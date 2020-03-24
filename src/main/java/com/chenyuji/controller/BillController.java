package com.chenyuji.controller;

import com.chenyuji.pojo.Bill;
import com.chenyuji.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bill")
public class BillController {
    @Autowired
    BillService billService;
    @RequestMapping("/insertBill")
    public void insert(HttpServletRequest request){
        Bill bill = new Bill();
        bill.setTypeId(1);
        billService.insertBill(bill);
        System.out.println("---");
    }

}
