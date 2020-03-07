package com.chenyuji.controller;


import com.alibaba.fastjson.JSONObject;
import com.chenyuji.util.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/login")
    public Map<String,Object> login(HttpServletRequest request) throws IOException {
        String code = request.getParameter("code");
        Map<String,Object> map = new HashMap();
        //登录凭证不能为空
        if(code == null||code.length() == 0){
            map.put("status",0);
            map.put("msg","code不能为空");
            return map;
        }
        //小程序唯一标识（在小程序管理后台获取）
        String wxspAppid = "wx01e7921a87e9b323";
        //小程序的app secret（在小程序管理后台获取）
        String wxspSecret = "9ec1e537ae4d0e20b7889f131d65a662";
        //授权（必填）
        String grant_type = "authorization_code";
        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");
        //根据openid查询数据库是否有数据
   

        String accessToken = getAccessToken(code);
        getUserInfo(accessToken,openid);

        System.out.println(openid);
        return null;
    }

    public String getAccessToken(String code) throws IOException {
        SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx01e7921a87e9b323&secret=9ec1e537ae4d0e20b7889f131d65a662";
        //String url = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=client_credential&appid=wx01e7921a87e9b323&secret=9ec1e537ae4d0e20b7889f131d65a662";

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String response = httpClient.execute(httpget, responseHandler);
        JSONObject OpenidJSONO = JSONObject.parseObject(response);
        String accessToken = String.valueOf(OpenidJSONO.get("access_token"));
        return accessToken;

    }
    public void getUserInfo(String accessToken,String openId) throws IOException {
        SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
        //String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String response = httpClient.execute(httpget, responseHandler);
        JSONObject OpenidJSONO = JSONObject.parseObject(response);
    }


}
