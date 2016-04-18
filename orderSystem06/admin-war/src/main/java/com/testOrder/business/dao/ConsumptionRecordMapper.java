package com.testOrder.business.dao;

import com.testOrder.business.model.ConsumptionRecord;
import com.testOrder.business.model.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ConsumptionRecordMapper {
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
    int deleteByPrimaryKey(Integer consumptionId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(ConsumptionRecord record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(ConsumptionRecord record);

    /**
     * 根据条件查询记录集
     */
    List<ConsumptionRecord> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    ConsumptionRecord selectByPrimaryKey(Integer consumptionId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") ConsumptionRecord record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") ConsumptionRecord record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(ConsumptionRecord record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(ConsumptionRecord record);

    /**
     * 根据点餐地点实现分组查询
     * @param example
     * @return
     */
    List<ConsumptionRecord> selectOrderByPlace(Criteria example);

    /**
     * 领取详情
     * @param criteria
     * @return
     */
    List<ConsumptionRecord> selectGetMealInfo(Criteria criteria);
}