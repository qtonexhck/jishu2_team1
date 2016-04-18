package com.testOrder.business.model;

import java.io.Serializable;

public class DishRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 记录id
     */
    private Integer id;

    /**
     * 菜单Id
     */
    private int dishId;

    /**
     * 发布时间
     */
    private String creTime;

    /**
     * 创建人，即菜单发布人
     */
    private String creMan;

    /**
     * 最大份数
     */
    private Short max;

    /**
     * 剩余数
     */
    private Short remain;

    private Dish dish;

    /**
     * @return 记录id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            记录id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 菜单Id
     */
    public int getDishId() {
        return dishId;
    }

    /**
     * @param dishId 
	 *            菜单Id
     */
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    /**
     * @return 发布时间
     */
    public String getCreTime() {
        return creTime;
    }

    /**
     * @param creTime 
	 *            发布时间
     */
    public void setCreTime(String creTime) {
        this.creTime = creTime;
    }

    /**
     * @return 创建人，即菜单发布人
     */
    public String getCreMan() {
        return creMan;
    }

    /**
     * @param creMan 
	 *            创建人，即菜单发布人
     */
    public void setCreMan(String creMan) {
        this.creMan = creMan;
    }

    /**
     * @return 最大份数
     */
    public Short getMax() {
        return max;
    }

    /**
     * @param max 
	 *            最大份数
     */
    public void setMax(Short max) {
        this.max = max;
    }

    /**
     * @return 剩余数
     */
    public Short getRemain() {
        return remain;
    }

    /**
     * @param remain 
	 *            剩余数
     */
    public void setRemain(Short remain) {
        this.remain = remain;
    }

    @Override
    public String toString() {
        return "DishRecord{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", creTime='" + creTime + '\'' +
                ", creMan='" + creMan + '\'' +
                ", max=" + max +
                ", remain=" + remain +
                ", dish=" + dish +
                '}';
    }

    /**
     * java.lang.Object#toString()
     */


    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}