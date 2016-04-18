package com.testOrder.business.model;

import java.io.Serializable;

public class Dish implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 套餐id
     */
    private Integer dishId;

    /**
     * 套餐菜名
     */
    private String dishName;

    /**
     * 单价
     */
    private Short price;

    /**
     * 厨师负责人
     */
    private String cook;

    /**
     * 菜式图片路径
     */
    private int dishPic;

    /**
     * @return 套餐id
     */
    public Integer getDishId() {
        return dishId;
    }

    /**
     * @param dishId 
	 *            套餐id
     */
    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    /**
     * @return 套餐菜名
     */
    public String getDishName() {
        return dishName;
    }

    /**
     * @param dishName 
	 *            套餐菜名
     */
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    /**
     * @return 单价
     */
    public Short getPrice() {
        return price;
    }

    /**
     * @param price 
	 *            单价
     */
    public void setPrice(Short price) {
        this.price = price;
    }

    /**
     * @return 厨师负责人
     */
    public String getCook() {
        return cook;
    }

    /**
     * @param cook 
	 *            厨师负责人
     */
    public void setCook(String cook) {
        this.cook = cook;
    }

    /**
     * @return 菜式图片路径
     */
    public int getDishPic() {
        return dishPic;
    }

    /**
     * @param dishPic 
	 *            菜式图片路径
     */
    public void setDishPic(int dishPic) {
        this.dishPic = dishPic;
    }

    /**
     * java.lang.Object#toString()
     */
    public String toString() {
        return "Dish [ dishId=" + dishId +",   dishName=" + dishName +",   price=" + price +",   cook=" + cook +",   dishPic=" + dishPic + " ] " ;
    }
}