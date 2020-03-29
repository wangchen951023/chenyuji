package com.chenyuji.service;

import com.chenyuji.pojo.Bill;

import java.util.List;

public interface BillService {
    int insertBill(Bill bill);
    List<Bill> getBillByLoginId(String adminLoginId,int page,int rows);
}
