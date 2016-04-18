package com.testOrder.business.model;

import java.io.Serializable;

public class WxUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 微信的OpenId
     */
    private String openId;

    /**
     * 手机号，必填，作为身份凭证
     */
    private String phoneNumber;

    /**
     * 微信号的昵称
     */
    private String nickname;

    /**
     * 性别：0代表未知；1代表男；2代表女
     */
    private String sex;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 微信头像路径
     */
    private String headimgurl;

    /**
     * 语言
     */
    private String language;

    /**
     * 动力加用户id
     */
    private String dljId;

    /**
     * @return 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 微信的OpenId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId 
	 *            微信的OpenId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * @return 手机号，必填，作为身份凭证
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber 
	 *            手机号，必填，作为身份凭证
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return 微信号的昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname 
	 *            微信号的昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return 性别：0代表未知；1代表男；2代表女
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex 
	 *            性别：0代表未知；1代表男；2代表女
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city 
	 *            城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country 
	 *            国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province 
	 *            省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return 微信头像路径
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * @param headimgurl 
	 *            微信头像路径
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    /**
     * @return 语言
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language 
	 *            语言
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return 动力加用户id
     */
    public String getDljId() {
        return dljId;
    }

    /**
     * @param dljId 
	 *            动力加用户id
     */
    public void setDljId(String dljId) {
        this.dljId = dljId;
    }

    /**
     * java.lang.Object#toString()
     */
    public String toString() {
        return "WxUser [ id=" + id +",   openId=" + openId +",   phoneNumber=" + phoneNumber +",   nickname=" + nickname +",   sex=" + sex +",   city=" + city +",   country=" + country +",   province=" + province +",   headimgurl=" + headimgurl +",   language=" + language +",   dljId=" + dljId + " ] " ;
    }
}