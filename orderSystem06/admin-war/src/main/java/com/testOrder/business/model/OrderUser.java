package com.testOrder.business.model;

import java.io.Serializable;

public class OrderUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String username;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * java.lang.Object#toString()
     */
    public String toString() {
        return "OrderUser [ userId=" + userId +",   username=" + username + " ] " ;
    }
}