package com.testOrder.business.service;

import com.testOrder.business.dao.PcUserMapper;
import com.testOrder.business.model.Criteria;
import com.testOrder.business.model.PcUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * pc_user
 * @version 1.0 2016-04-01
 * @powerby hetgyd 
 */
@Service
public class PcUserService {
    @Autowired
    private PcUserMapper pcUserMapper;

    public int countByExample(Criteria example) {
        int count = this.pcUserMapper.countByExample(example);
        return count;
    }

    public PcUser selectByPrimaryKey(Integer userId) {
        return this.pcUserMapper.selectByPrimaryKey(userId);
    }

    public List<PcUser> selectByExample(Criteria example) {
        return this.pcUserMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Integer userId) {
        return this.pcUserMapper.deleteByPrimaryKey(userId);
    }

    public int updateByPrimaryKeySelective(PcUser record) {
        return this.pcUserMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(PcUser record) {
        return this.pcUserMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.pcUserMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(PcUser record, Criteria example) {
        return this.pcUserMapper.updateByExampleSelective(record, example.getCondition());
    }

    public int updateByExample(PcUser record, Criteria example) {
        return this.pcUserMapper.updateByExample(record, example.getCondition());
    }

    public int insert(PcUser record) {
        return this.pcUserMapper.insert(record);
    }

    public int insertSelective(PcUser record) {
        return this.pcUserMapper.insertSelective(record);
    }
}