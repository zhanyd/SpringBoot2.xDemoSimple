package com.zhanyd.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhanyd.app.mapper.UserInfoMapper;
import com.zhanyd.app.model.UserInfo;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

	@Autowired
	UserInfoMapper userInfoMapper;
	
	public int insertSelective(UserInfo record){
		return userInfoMapper.insertSelective(record);
	}
	
	public UserInfo selectByPrimaryKey(Integer id){
		return userInfoMapper.selectByPrimaryKey(id);
	}
	
	public UserInfo selectByUnionid(String unionid){
		return userInfoMapper.selectByUnionid(unionid);
	}


}
