package com.testOrder.business.service;

import com.testOrder.business.dao.DishRecordMapper;
import com.testOrder.business.model.Criteria;
import com.testOrder.business.model.DishRecord;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * dish_record
 * @version 1.0 2016-04-06
 * @powerby hetgyd 
 */
@Service
public class DishRecordService {
    @Autowired
    private DishRecordMapper dishRecordMapper;

    public int countByExample(Criteria example) {
        int count = this.dishRecordMapper.countByExample(example);
        return count;
    }

    public DishRecord selectByPrimaryKey(Integer id) {
        return this.dishRecordMapper.selectByPrimaryKey(id);
    }

    public List<DishRecord> selectByExample(Criteria example) {
        return this.dishRecordMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.dishRecordMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(DishRecord record) {
        return this.dishRecordMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(DishRecord record) {
        return this.dishRecordMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.dishRecordMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(DishRecord record, Criteria example) {
        return this.dishRecordMapper.updateByExampleSelective(record, example.getCondition());
    }

    public int updateByExample(DishRecord record, Criteria example) {
        return this.dishRecordMapper.updateByExample(record, example.getCondition());
    }

    public int insert(DishRecord record) {
        return this.dishRecordMapper.insert(record);
    }

    public int insertSelective(DishRecord record) {
        return this.dishRecordMapper.insertSelective(record);
    }

    /**
     * 获取今天的菜单信息
     * @param criteria
     * @return
     */
    public List<DishRecord> selectTodayDish(Criteria criteria) {
        return this.dishRecordMapper.selectTodayDish(criteria);
    }
}