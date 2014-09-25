package demo.ldap;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.AuthenticationSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;


public class LdapTemplateTest {
	private LdapTemplate ldapTemplate;
	protected AttributesMapper CN_ATTRIBUTES_MAPPER = null;
	 
	
	@Before
	public void init(){
		LdapContextSource cs = new LdapContextSource();
	    cs.setCacheEnvironmentProperties(false);
	    cs.setUrl("ldap://172.17.16.69:10389");
	    cs.setBase("dc=wlt,dc=com");
	    cs.setAuthenticationSource(new AuthenticationSource() {
	        public String getCredentials() {
		    return "123456";
		}
	 
		public String getPrincipal() {
		    return "uid=um,ou=users,ou=system";
		}
	    });
	    ldapTemplate = new LdapTemplate(cs);
	}
	@Test
	public void add(){
		for(int i=0;i<30;i++){
		UmUser user = new UmUser();
		String nowStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		user.setCreateTime(nowStr);
		user.setUpdateTime(nowStr);
		user.setUmid("chang"+i);
		user.setCn(user.getUmid());
		user.setEmail("umdev@172.17.16.65"); 
		user.setUserPassword("{MD5}"+DigestUtils.md5Hex("123456"));
		user.setUserName("sname"); 
		user.setBirthday("2011-11-11");
		user.setDepartmentId("ou=caiwu");
		user.setDepartmentName("财务部");
		user.setGender("男");
		user.setMobile("123456");
		user.setOpUmid("test");
		user.setStatus("1");
		user.setTelphone("555555");
		user.setTitle("title");
		user.setUserDesc("userdesc.......");
		ldapTemplate.create(user);
		}
	}
	
	@Test
	public void getByDn(){
		ldapTemplate.findOne(query().where("uid").is("changpengfei"), UmUser.class);
	}
	@Test
	public void delete(){
		ldapTemplate.unbind("uid=chang,ou=inner,ou=people");
	}
	
	@Test
	public void deleteById(){
		UmUser umUser=new UmUser();
		umUser.setUmid("chang4");
		ldapTemplate.delete(umUser);
	}
	
	@Test
	public void update(){
		UmUser umUser=new UmUser();
		umUser.setUmid("chang5");
		umUser.setEmail("xxxx");
		umUser.setMobile("1234567890");
		LdapUtil.setId(umUser);
		ldapTemplate.update(umUser);
	}
	
	@Test
	public void findAll(){
		 List<UmUser> list= ldapTemplate.findAll(UmUser.class);
		 System.out.println(list.size());
	}
	
	@Test
	public void findByLastName(){
		 List<UmUser> list=ldapTemplate.find(query().
				 where("status").is("1").and("title").is("title1").and("status").is("1"), UmUser.class);
		 System.out.println(list.size());
		 list=ldapTemplate.find(query().
				 where("status").is("3").or("title").is("title1").or("title").is("title3"), UmUser.class);
		 System.out.println(list.size());
	}
	
	@Test
	 public void getAllPersonNames() {
	     List list=  ldapTemplate.search(
	         query().where("objectclass").is("person"),
	         new AttributesMapper<String>() {
	            public String mapFromAttributes(Attributes attrs)
	               throws NamingException, javax.naming.NamingException {
	               return attrs.get("cn").get().toString();
	            }
	         });
	     System.out.println(list.size());
	   }
	
	
		private class PersonAttributesMapper implements AttributesMapper<UmUser> {
	      public UmUser mapFromAttributes(Attributes attrs) throws NamingException, javax.naming.NamingException {
	    	 UmUser person = new UmUser();
	         person.setEmail((String)attrs.get("mail").get());
	         return person;
	      }
	   }
		
		@Test
	   public void  getAllPersons() {
			List<UmUser>list= ldapTemplate.search(query()
	          .where("objectclass").is("umUser"), new PersonAttributesMapper());
			System.out.println(list.size());
	   }
		@Test
		public void updateU() {
			UmUser umUser=new UmUser();
			umUser.setUmid("chang5");
			umUser.setEmail("xxxxx");
			umUser.setMobile("1234567890");
	        Name dn = LdapUtil.buildDn(umUser);
//	        Attribute attr = new BasicAttribute("mail",umUser.getEmail());
	        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("mail",umUser.getEmail()));
	        ModificationItem item1 = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("mobile",umUser.getMobile()));
	        ldapTemplate.modifyAttributes(dn, new ModificationItem[] {item,item1});
		 }
		
		@Test
		public void updateOgnize() throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
			UmUser umUser=new UmUser();
			umUser.setUmid("chang5");
			umUser.setEmail("45337404@qq.com");
			umUser.setMobile("18768868784");
			umUser.setTitle("hahah");
	        Name dn = LdapUtil.buildDn(umUser);
	        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("mail","9000"));
	        ModificationItem item1 = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("title","1000"));
	        ModificationItem[] modificationItem=new ModificationItem[2];
	        modificationItem[0]=item;
	        modificationItem[1]=item1;
	        ldapTemplate.modifyAttributes(dn,modificationItem);
			LdapUtil.commonUpdate(umUser,ldapTemplate);
		 }
		
		@Test
		public void temp() throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
			UmUser umUser=new UmUser();
			umUser.setUmid("chang5");
			umUser.setEmail("45337404@qq.com");
			umUser.setMobile("18768868784");
			umUser.setTitle("hahah");
	        Name dn = LdapUtil.buildDn(umUser);
	        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("mail","9000"));
	        ModificationItem item1 = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("title","1000"));
	        ModificationItem[] modificationItem=new ModificationItem[2];
	        modificationItem[0]=item;
	        modificationItem[1]=item1;
	        ldapTemplate.modifyAttributes(dn,modificationItem);
//	        ldapTemplate.lo
		 }
	
	}
