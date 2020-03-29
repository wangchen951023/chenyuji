package com.chenyuji.service.impl;

import com.chenyuji.mapper.BillMapper;
import com.chenyuji.pojo.Bill;
import com.chenyuji.service.BillService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;

    @Override
    public int insertBill(Bill bill) {

        return billMapper.insert(bill);
    }

    @Override
    public List<Bill> getBillByLoginId(String adminLoginId,int page,int rows) {
        PageHelper.startPage(page,rows);
        return billMapper.getBillByLoginId(adminLoginId);
    }
}
