package com.testOrder.business.dao;

import com.testOrder.business.model.Criteria;
import com.testOrder.business.model.Dish;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface DishMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer dishId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Dish record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Dish record);

    /**
     * 根据条件查询记录集
     */
    List<Dish> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    Dish selectByPrimaryKey(Integer dishId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") Dish record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") Dish record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Dish record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Dish record);
}