package demo.ldap;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;


/**
 * 
 * <br>
 * <b>功能：</b>UmUserEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> Feb 2, 2014 <br>
 * <b>版权所有：<b>版权所有(C) 2014, 平安金融科技数据中心<br>
 */
@Entry(objectClasses={"umUser", "inetOrgPerson", "organizationalPerson", "person", "top"},base="ou=inner,ou=people")
public class UmUser  {
	
	@Id
	private Name dn;
	
//	@Transient
//	private String rid; // 保存 dn 字符串  (rid 代表一条记录的主键，不包含baseDn的  dn)
	
	@DnAttribute(value="uid", index=0)
	@Attribute(name="uid")
	private String umid; 
	
	@Attribute(name="cn")
	private String cn;    //他的指等同于umid ，因为这个是必填项，所以需要把umid的值复制到cn内
	
	@Attribute(name="sn")
	private String userName;
	
	private String userPassword;
	
	@Attribute(name="description")
	private String userDesc;
	
	private String gender;
	private String birthday;
	
	@Attribute(name="mail")
	private String email;
	
	@Attribute(name="telephoneNumber")
	private String telphone;
	
	@Attribute(name="mobile")
	private String mobile;
	private String title;
	
	@Attribute(name="organizationalUnitName")
	private String departmentId;
	
	@Attribute(name="organizationName")
	private String departmentName;
	private String status;
	private String createTime;
	private String updateTime;
	private String opUmid;

//	public String getRid() {
//		return this.rid;
//	}
//
//	public void setRid(String rid) {
//		this.rid = rid;
//	}

	public String getUmid() {
		return this.umid;
	}

	public void setUmid(String umid) {
		this.umid = umid;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserDesc() {
		return this.userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getOpUmid() {
		return this.opUmid;
	}

	public void setOpUmid(String opUmid) {
		this.opUmid = opUmid;
	}

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}
}

