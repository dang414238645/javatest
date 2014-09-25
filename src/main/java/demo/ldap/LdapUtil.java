package demo.ldap;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.core.ObjectDirectoryMapper;
import org.springframework.ldap.odm.core.impl.DefaultObjectDirectoryMapper;
import org.springframework.ldap.support.LdapNameBuilder;


public class LdapUtil {
	private static ObjectDirectoryMapper odm = new DefaultObjectDirectoryMapper();
	
	public static void setId(Object entry){
		Name id = odm.getId(entry);
        if(id == null) {
            id = odm.getCalculatedId(entry);
            odm.setId(entry, id);
        }
	}
	/**
	 * 根据dn字符串构建Dn  （包含baseDn）
	 * @param dnStr
	 * @return
	 */
	public static Name buildDn(String dnStr) {
		return LdapNameBuilder.newInstance().add(dnStr + "," + getBaseDn()).build();
	}
	/**
	 * 获取sysConfig.properties 配置文件内 baseDn 的路径
	 * @return
	 */
	public static String getBaseDn() {
		return "dc=wlt,dc=com";
	}
	public static Name buildDn(Object entry){
		Name id = odm.getId(entry);
        if(id == null) {
            id = odm.getCalculatedId(entry);
        }
        return id;
	}
	public static Attributes buildAttributes(Object entry){
//		LdapUtils.getStringValue(name, key)
		  Attributes attrs = new BasicAttributes();
	      BasicAttribute ocattr = new BasicAttribute("objectclass");
	      ocattr.add("top");
	      ocattr.add("person");
	      attrs.put(ocattr);
	      attrs.put("cn", "Some Person");
	      attrs.put("sn", "Person");
	      return attrs;
	}
	public static void commonUpdate(UmUser umUser, LdapTemplate ldapTemplate) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
		Field[]fields=umUser.getClass().getDeclaredFields();
		boolean isId=false;
		Name dn = LdapUtil.buildDn(umUser);
		List<Field> fieldsTemp=new ArrayList<Field>();
		for(Field field:fields){
			Annotation[]annotations=field.getAnnotations();
			for(Annotation annotation:annotations){
				System.out.println(annotation.annotationType().getSimpleName().equals("Id"));
				if(annotation.annotationType().getSimpleName().equals("Id")||annotation.annotationType().getSimpleName().equals("DnAttribute")){
					isId=true;
				}
			}
			field.setAccessible( true );
	        Object val = field.get(umUser); 
	        if(val!=null&&!isId){
	        	fieldsTemp.add(field);
	        }
	        isId=false;
		}
		
		
		ModificationItem[] modificationItem=new ModificationItem[fieldsTemp.size()];
		for(int i=0;i<fieldsTemp.size();i++){
			Field field=fieldsTemp.get(i);
			String fieldName=field.getName();
			
			Attribute annotation=field.getAnnotation(Attribute.class);
			if(annotation!=null)
				fieldName=annotation.name();
//			String type=field.getType().getName();
//			Method method=umUser.getClass().getMethod("get"+new StringBuffer().append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString());
			
			ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute(fieldName,field.get(umUser)));
//			ModificationItem item=null;
//			if(type.equals("java.lang.String")){  
//				 item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute(field.getName(),(String)field.get(umUser)));  
//             }  
			modificationItem[i]=item;
		}
		
		ldapTemplate.modifyAttributes(dn, modificationItem);
	}
}
