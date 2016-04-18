package com.testOrder.business.model;

import java.io.Serializable;

public class ConsumptionRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 消费记录id<br>
	 *             
     */
    private Integer consumptionId;

    /**
     * 消费时间
     */
    private String time;

    /**
     * 消费价格
     */
    private Short price;

    /**
     * 消费地点
     */
    private String place;

    /**
     * 微信OpenId
     */
    private String wxOpenId;

    /**
     * 动力加id
     */
    private String dljId;

    /**
     * PC消费用户的id
     */
    private Integer customerId;

    /**
     * 菜名id
     */
    private Integer dishId;

    /**
     * 消费状态，默认0，未消费；1，已消费
     */
    private String consumedSt;

    private PcUser pcUser;

    /**
     * @return 消费记录id<br>
	 *                     
     */
    public Integer getConsumptionId() {
        return consumptionId;
    }

    /**
     * @param consumptionId 
	 *            消费记录id<br>
	 *                        
     */
    public void setConsumptionId(Integer consumptionId) {
        this.consumptionId = consumptionId;
    }

    /**
     * @return 消费时间
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time 
	 *            消费时间
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return 消费价格
     */
    public Short getPrice() {
        return price;
    }

    /**
     * @param price 
	 *            消费价格
     */
    public void setPrice(Short price) {
        this.price = price;
    }

    /**
     * @return 消费地点
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place 
	 *            消费地点
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @return 微信OpenId
     */
    public String getWxOpenId() {
        return wxOpenId;
    }

    /**
     * @param wxOpenId 
	 *            微信OpenId
     */
    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    /**
     * @return 动力加id
     */
    public String getDljId() {
        return dljId;
    }

    /**
     * @param dljId 
	 *            动力加id
     */
    public void setDljId(String dljId) {
        this.dljId = dljId;
    }

    /**
     * @return PC消费用户的id
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId 
	 *            PC消费用户的id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * @return 菜名id
     */
    public Integer getDishId() {
        return dishId;
    }

    /**
     * @param dishId 
	 *            菜名id
     */
    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    /**
     * @return 消费状态，默认0，未消费；1，已消费
     */
    public String getConsumedSt() {
        return consumedSt;
    }

    /**
     * @param consumedSt 
	 *            消费状态，默认0，未消费；1，已消费
     */
    public void setConsumedSt(String consumedSt) {
        this.consumedSt = consumedSt;
    }

    public PcUser getPcUser() {
        return pcUser;
    }

    public void setPcUser(PcUser pcUser) {
        this.pcUser = pcUser;
    }

    /**
     * java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConsumptionRecord{" +
                "consumptionId=" + consumptionId +
                ", time='" + time + '\'' +
                ", price=" + price +
                ", place='" + place + '\'' +
                ", wxOpenId='" + wxOpenId + '\'' +
                ", dljId='" + dljId + '\'' +
                ", customerId=" + customerId +
                ", dishId=" + dishId +
                ", consumedSt='" + consumedSt + '\'' +
                ", pcUser=" + pcUser +
                '}';
    }
}