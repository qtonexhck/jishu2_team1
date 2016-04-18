package com.testOrder.business.model;

import java.io.Serializable;

public class PcUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 账号
     */
    private String userNo;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 用户头像路径
     */
    private String pic;

    /**
     * 用户手机号码
     */
    private String mobile;

    /**
     * 用户座机
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private String regCrdate;

    /**
     * 账号是否生效，0代表不生效，1代表生效
     */
    private String isChecked;

    /**
     * 性别，0男，1女
     */
    private String sex;

    /**
     * 饭卡账户
     */
    private Integer account;

    /**
     * 用户累型，0用户，1管理员
     */
    private String userType;

    /**
     * @return 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return 账号
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * @param userNo 
	 *            账号
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    /**
     * @return 用户姓名
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            用户姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 密码
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd 
	 *            密码
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * @return 用户头像路径
     */
    public String getPic() {
        return pic;
    }

    /**
     * @param pic 
	 *            用户头像路径
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * @return 用户手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile 
	 *            用户手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return 用户座机
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel 
	 *            用户座机
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email 
	 *            邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return 创建时间
     */
    public String getRegCrdate() {
        return regCrdate;
    }

    /**
     * @param regCrdate 
	 *            创建时间
     */
    public void setRegCrdate(String regCrdate) {
        this.regCrdate = regCrdate;
    }

    /**
     * @return 账号是否生效，0代表不生效，1代表生效
     */
    public String getIsChecked() {
        return isChecked;
    }

    /**
     * @param isChecked 
	 *            账号是否生效，0代表不生效，1代表生效
     */
    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    /**
     * @return 性别，0男，1女
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex 
	 *            性别，0男，1女
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return 饭卡账户
     */
    public Integer getAccount() {
        return account;
    }

    /**
     * @param account 
	 *            饭卡账户
     */
    public void setAccount(Integer account) {
        this.account = account;
    }

    /**
     * @return 用户累型，0用户，1管理员
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType 
	 *            用户累型，0用户，1管理员
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * java.lang.Object#toString()
     */
    public String toString() {
        return "PcUser [ userId=" + userId +",   userNo=" + userNo +",   name=" + name +",   passwd=" + passwd +",   pic=" + pic +",   mobile=" + mobile +",   tel=" + tel +",   email=" + email +",   regCrdate=" + regCrdate +",   isChecked=" + isChecked +",   sex=" + sex +",   account=" + account +",   userType=" + userType + " ] " ;
    }
}