package com.testOrder.business.service;

import com.testOrder.business.dao.OrderUserMapper;
import com.testOrder.business.model.Criteria;
import com.testOrder.business.model.OrderUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * order_user
 * @version 1.0 2016-03-31
 * @powerby hetgyd 
 */
@Service
public class OrderUserService {
    @Autowired
    private OrderUserMapper orderUserMapper;

    public int countByExample(Criteria example) {
        int count = this.orderUserMapper.countByExample(example);
        return count;
    }

    public OrderUser selectByPrimaryKey(Integer userId) {
        return this.orderUserMapper.selectByPrimaryKey(userId);
    }

    public List<OrderUser> selectByExample(Criteria example) {
        return this.orderUserMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Integer userId) {
        return this.orderUserMapper.deleteByPrimaryKey(userId);
    }

    public int updateByPrimaryKeySelective(OrderUser record) {
        return this.orderUserMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(OrderUser record) {
        return this.orderUserMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.orderUserMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(OrderUser record, Criteria example) {
        return this.orderUserMapper.updateByExampleSelective(record, example.getCondition());
    }

    public int updateByExample(OrderUser record, Criteria example) {
        return this.orderUserMapper.updateByExample(record, example.getCondition());
    }

    public int insert(OrderUser record) {
        return this.orderUserMapper.insert(record);
    }

    public int insertSelective(OrderUser record) {
        return this.orderUserMapper.insertSelective(record);
    }
}