package com.chenyuji.service.impl;

import com.chenyuji.mapper.BillMapper;
import com.chenyuji.pojo.Bill;
import com.chenyuji.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;

    @Override
    public int insertBill(Bill bill) {

        return billMapper.insert(bill);
    }
}
