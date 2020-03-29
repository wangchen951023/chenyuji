package com.chenyuji.mapper;

import com.chenyuji.pojo.Bill;

import java.util.List;

public interface BillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);
    List<Bill> getBillByLoginId(String admnLoginId);
}