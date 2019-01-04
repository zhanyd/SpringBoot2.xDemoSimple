package com.zhanyd.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhanyd.app.model.IdentifyingCode;

public interface IdentifyingCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IdentifyingCode record);

    int insertSelective(IdentifyingCode record);

    IdentifyingCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IdentifyingCode record);

    int updateByPrimaryKey(IdentifyingCode record);
    
    List<Map<String,Object>> selectByParam(Map<String,String> param);

    int checkIdentifyingCode(@Param(value="tel")String tel, @Param(value="identifyingCode")String identifyingCode);

    int getTimeSpanFromNow(@Param(value="tel")String tel);

    int selectTodaySendCount(@Param(value="tel")String tel);
}