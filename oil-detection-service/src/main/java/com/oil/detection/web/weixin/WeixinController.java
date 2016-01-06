package com.oil.detection.web.weixin;

import com.google.common.collect.Maps;
import com.oil.detection.http.HttpClientUtils;
import com.oil.detection.util.StringUtils;
import com.oil.detection.web.base.BaseControllor;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信相关接口
 */
@Controller
@RequestMapping(value = "/weixin")
public class WeixinController extends BaseControllor {
    private static final Logger logger = Logger.getLogger(WeixinController.class);

    @RequestMapping(value = "/access", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String access(String signature, String timestamp, String nonce, String echostr) {
        System.out.println(echostr);
        return echostr;
    }


    @RequestMapping(value = "/token", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String token() throws Exception {

        Map<String, String> map = Maps.newHashMap();
        map.put("grant_type", "client_credential");
        map.put("appid", "wxd4ae59a92d544688");
        map.put("secret", "a5a8cd948738e061b5892272dc34ce3b");
        String str = HttpClientUtils.get("https://api.weixin.qq.com/cgi-bin/token", map);
        return str;
    }

    @RequestMapping(value = "/http", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String http(Map<String, String> map, String url) throws Exception {
        String str = HttpClientUtils.get("https://api.weixin.qq.com/cgi-bin/token", map);
        System.out.println(str);
        return str;
    }


    @RequestMapping(value = "/createMenu", method = {RequestMethod.POST, RequestMethod.GET}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String createMenu(String json, String token) throws Exception {
        RequestEntity stringEntity;
        if (StringUtils.isNotBlank(json)) {
            stringEntity = new StringRequestEntity(json, "", "utf-8");
        } else {
            stringEntity = new StringRequestEntity("{\"button\":[{\"type\":\"view\",\"name\":\"找油\",\"url\":\"http://123.57.236.102:8082/index\"},{\"type\":\"view\",\"name\":\"联系我们\",\"url\":\"\"}]}", "", "utf-8");
        }
        try {
            String post = HttpClientUtils.post("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token, new HashMap<String, String>(), stringEntity);
            System.out.println(post);
            return post;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
