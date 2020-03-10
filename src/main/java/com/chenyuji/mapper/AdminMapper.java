package com.chenyuji.mapper;

import com.chenyuji.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;


public interface AdminMapper {
    int deleteByPrimaryKey(String loginId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(String loginId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}