package com.testOrder.business.service;

import com.testOrder.business.dao.WxUserMapper;
import com.testOrder.business.model.Criteria;
import com.testOrder.business.model.WxUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * wx_user
 * @version 1.0 2016-04-08
 * @powerby hetgyd 
 */
@Service
public class WxUserService {
    @Autowired
    private WxUserMapper wxUserMapper;

    public int countByExample(Criteria example) {
        int count = this.wxUserMapper.countByExample(example);
        return count;
    }

    public WxUser selectByPrimaryKey(Integer id) {
        return this.wxUserMapper.selectByPrimaryKey(id);
    }

    public List<WxUser> selectByExample(Criteria example) {
        return this.wxUserMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.wxUserMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(WxUser record) {
        return this.wxUserMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WxUser record) {
        return this.wxUserMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.wxUserMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(WxUser record, Criteria example) {
        return this.wxUserMapper.updateByExampleSelective(record, example.getCondition());
    }

    public int updateByExample(WxUser record, Criteria example) {
        return this.wxUserMapper.updateByExample(record, example.getCondition());
    }

    public int insert(WxUser record) {
        return this.wxUserMapper.insert(record);
    }

    public int insertSelective(WxUser record) {
        return this.wxUserMapper.insertSelective(record);
    }
}