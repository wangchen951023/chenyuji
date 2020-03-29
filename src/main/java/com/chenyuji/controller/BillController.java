package com.chenyuji.controller;

import com.chenyuji.pojo.Admin;
import com.chenyuji.pojo.Bill;
import com.chenyuji.service.AdminService;
import com.chenyuji.service.BillService;
import com.chenyuji.util.DateUtil;
import com.chenyuji.util.FileUtil;
import com.chenyuji.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillService billService;


    @RequestMapping("/getBillByLoginId")
    @ResponseBody
    public Map<String,Object> getBillByLoginId(HttpServletRequest request){
        String adminLoginId = request.getParameter("login_id");
        int page = Integer.parseInt(request.getParameter("page"));
        int rows = Integer.parseInt(request.getParameter("rows"));
        List<Bill> billList = billService.getBillByLoginId(adminLoginId,page,rows);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("billList",billList);
        return map;
    }


    @RequestMapping("/addBillSimple")
    @ResponseBody
    public Map<String,Object> addBillSimple(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("status","error");
        Bill bill = new Bill();
        bill.setAdminLoginId(request.getParameter("login_id"));
        bill.setTypeId(Integer.parseInt(request.getParameter("bill_type_id")));
        bill.setTitle(request.getParameter("bill_title"));
        bill.setAmount(request.getParameter("bill_amount"));
        bill.setContent(request.getParameter("bill_content"));
        int i =billService.insertBill(bill);
        if (i>0){
            map.put("status","success");
        }
        return map;
    }

    @RequestMapping("/addBillDetail")
    @ResponseBody
    public String addBillDetail(String bill_title,String bill_amount,String bill_content,String login_id,HttpServletRequest request){

        Bill bill = new Bill();
        bill.setTitle(bill_title);
        bill.setAmount(bill_amount);
        bill.setContent(bill_content);
        bill.setTypeId(Integer.parseInt(request.getParameter("bill_type_id")));
        bill.setAdminLoginId(login_id);
        String fileUrl = "";
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;

        Map<String, MultipartFile> map= mRequest.getFileMap();

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            MultipartFile mFile = (MultipartFile) entry.getValue();
            String origFilename = mFile.getOriginalFilename();
            String fileExt = origFilename.substring(origFilename.lastIndexOf("."));
            try {
                String dayPath = DateUtil.formatDate(new Date(), "yyyy-MM-dd") + "/";
                System.out.println(dayPath);
                String savePath = "/data/webs/uploadFiles/" + dayPath;
                System.out.println(savePath);
                FileUtil.makeDir(new File(savePath));
                System.out.println("文件夹创建结束");
                String day = DateUtil.formatDate(new Date(), "yyyy-MM-dd-HH-mm-ss");
                String randStr = StringUtil.randStringWithoutSms(4);
                String newFilename = "files-" + "-" + day + "-" + randStr + fileExt;
                System.out.println(newFilename);
                FileCopyUtils.copy(mFile.getBytes(), new File(savePath + newFilename));
                fileUrl = fileUrl+savePath + newFilename+";";
            }catch (IOException e){

            }

        }
        bill.setUrl(fileUrl);
        billService.insertBill(bill);
        return "--";
    }




    /**
     * 图片上传测试用
     * author whangchen
     * @param comment
     * @param request
     */
    @RequestMapping("/addImg")
    @ResponseBody
    //@RequestParam(value = "imgsrc", required = false) MultipartFile[] files
    public void addImg(String comment,HttpServletRequest request){

        //System.out.println(file.getOriginalFilename());

        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        String s = (String) mRequest.getAttribute("comment");
        Map<String, MultipartFile> map= mRequest.getFileMap();
        //MultiValueMap<String, MultipartFile> map = mRequest.getMultiFileMap();//不好使
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();





            MultipartFile mFile = (MultipartFile) entry.getValue();
            String origFilename = mFile.getOriginalFilename();
            String fileExt = origFilename.substring(origFilename.lastIndexOf("."));
            try {
                String dayPath = DateUtil.formatDate(new Date(), "yyyy-MM-dd") + "/";
                String savePath = "/data/webs/uploadFiles/" + dayPath;
                FileUtil.makeDir(new File(savePath));
                String day = DateUtil.formatDate(new Date(), "yyyy-MM-dd-HH-mm-ss");
                String randStr = StringUtil.randStringWithoutSms(4);
                String newFilename = "files-" + "-" + day + "-" + randStr + fileExt;
                FileCopyUtils.copy(mFile.getBytes(), new File(savePath + newFilename));
            }catch (IOException e){

            }

        }





        /*MultipartHttpServletRequest multipartRequest =
                WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
        Iterator<String> files = mRequest.getFileNames();
        while (files.hasNext()) {
            MultipartFile mFile = mRequest.getFile(files.next());
            System.out.println(mFile.getOriginalFilename());
        }*/

    }



}
