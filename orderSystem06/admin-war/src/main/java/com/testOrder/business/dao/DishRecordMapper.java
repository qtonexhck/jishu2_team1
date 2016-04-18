package com.testOrder.business.dao;

import com.testOrder.business.model.Criteria;
import com.testOrder.business.model.DishRecord;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface DishRecordMapper {
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
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(DishRecord record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(DishRecord record);

    /**
     * 根据条件查询记录集
     */
    List<DishRecord> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    DishRecord selectByPrimaryKey(Integer id);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") DishRecord record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") DishRecord record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(DishRecord record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(DishRecord record);

    /**
     * 获取今天的菜单信息
     * @param criteria
     * @return
     */
    List<DishRecord> selectTodayDish(Criteria criteria);
}