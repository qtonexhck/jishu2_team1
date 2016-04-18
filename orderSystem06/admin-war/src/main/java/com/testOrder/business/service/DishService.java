package com.testOrder.business.service;

import com.testOrder.business.dao.DishMapper;
import com.testOrder.business.model.Criteria;
import com.testOrder.business.model.Dish;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * dish
 * @version 1.0 2016-04-06
 * @powerby hetgyd 
 */
@Service
public class DishService {
    @Autowired
    private DishMapper dishMapper;

    public int countByExample(Criteria example) {
        int count = this.dishMapper.countByExample(example);
        return count;
    }

    public Dish selectByPrimaryKey(Integer dishId) {
        return this.dishMapper.selectByPrimaryKey(dishId);
    }

    public List<Dish> selectByExample(Criteria example) {
        return this.dishMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Integer dishId) {
        return this.dishMapper.deleteByPrimaryKey(dishId);
    }

    public int updateByPrimaryKeySelective(Dish record) {
        return this.dishMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Dish record) {
        return this.dishMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.dishMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Dish record, Criteria example) {
        return this.dishMapper.updateByExampleSelective(record, example.getCondition());
    }

    public int updateByExample(Dish record, Criteria example) {
        return this.dishMapper.updateByExample(record, example.getCondition());
    }

    public int insert(Dish record) {
        return this.dishMapper.insert(record);
    }

    public int insertSelective(Dish record) {
        return this.dishMapper.insertSelective(record);
    }
}