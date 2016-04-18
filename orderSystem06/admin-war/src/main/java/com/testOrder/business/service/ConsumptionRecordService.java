package com.testOrder.business.service;

import com.testOrder.business.dao.ConsumptionRecordMapper;
import com.testOrder.business.model.ConsumptionRecord;
import com.testOrder.business.model.Criteria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * consumption_record
 * @version 1.0 2016-04-06
 * @powerby hetgyd 
 */
@Service
public class ConsumptionRecordService {
    @Autowired
    private ConsumptionRecordMapper consumptionRecordMapper;

    public int countByExample(Criteria example) {
        int count = this.consumptionRecordMapper.countByExample(example);
        return count;
    }

    public ConsumptionRecord selectByPrimaryKey(Integer consumptionId) {
        return this.consumptionRecordMapper.selectByPrimaryKey(consumptionId);
    }

    public List<ConsumptionRecord> selectByExample(Criteria example) {
        return this.consumptionRecordMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Integer consumptionId) {
        return this.consumptionRecordMapper.deleteByPrimaryKey(consumptionId);
    }

    public int updateByPrimaryKeySelective(ConsumptionRecord record) {
        return this.consumptionRecordMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ConsumptionRecord record) {
        return this.consumptionRecordMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.consumptionRecordMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(ConsumptionRecord record, Criteria example) {
        return this.consumptionRecordMapper.updateByExampleSelective(record, example.getCondition());
    }

    public int updateByExample(ConsumptionRecord record, Criteria example) {
        return this.consumptionRecordMapper.updateByExample(record, example.getCondition());
    }

    public int insert(ConsumptionRecord record) {
        return this.consumptionRecordMapper.insert(record);
    }

    public int insertSelective(ConsumptionRecord record) {
        return this.consumptionRecordMapper.insertSelective(record);
    }

    /**
     * 根据点餐地点实现分组查询
     * @param example
     * @return
     */
    public List<ConsumptionRecord> selectOrderByPlace(Criteria example) {
        return this.consumptionRecordMapper.selectOrderByPlace(example);
    }

    /**
     * 领取详情
     * @param criteria
     * @return
     */
    public List<ConsumptionRecord> selectGetMealInfo(Criteria criteria) {
        return this.consumptionRecordMapper.selectGetMealInfo(criteria);
    }
}