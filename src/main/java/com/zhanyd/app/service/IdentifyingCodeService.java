package com.zhanyd.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhanyd.app.mapper.IdentifyingCodeMapper;
import com.zhanyd.app.model.IdentifyingCode;


@Service
public class IdentifyingCodeService {

	 @Autowired
    IdentifyingCodeMapper identifyingCodeMapper;
	 
	public int insertSelective(IdentifyingCode record){
		return identifyingCodeMapper.insertSelective(record);
	}

    public List<Map<String,Object>> selectByParam(Map<String,String> param){
        return identifyingCodeMapper.selectByParam(param);
    }

    public int checkIdentifyingCode(String tel,String identifyingCode){
        return identifyingCodeMapper.checkIdentifyingCode(tel,identifyingCode);
    }

    public int getTimeSpanFromNow(String tel){
        return identifyingCodeMapper.getTimeSpanFromNow(tel);
    }

    public int selectTodaySendCount(String tel){
        return identifyingCodeMapper.selectTodaySendCount(tel);
    }
}
