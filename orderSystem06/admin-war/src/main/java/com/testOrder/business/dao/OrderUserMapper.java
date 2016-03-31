package com.testOrder.business.dao;

import com.testOrder.business.model.Criteria;
import com.testOrder.business.model.OrderUser;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface OrderUserMapper {
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
    int deleteByPrimaryKey(Integer userId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(OrderUser record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(OrderUser record);

    /**
     * 根据条件查询记录集
     */
    List<OrderUser> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    OrderUser selectByPrimaryKey(Integer userId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") OrderUser record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") OrderUser record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(OrderUser record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(OrderUser record);
}