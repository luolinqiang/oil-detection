/**
 *
 */
package com.oil.detection.common;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public enum ReturnCode {

    ACTIVE_EXCEPTION(-1, "异常"),
    ACTIVE_SUCCESS(0, "成功"),
    ACTIVE_FAILURE(1, "失败"),
    ERROR_PARAMS_NOT_NULL(2, "参数不能为空"),
    ERROR_PARAMS(400, "请求参数错误"),
    ERROR_AUTH(401, "无权限"),
    ERROR_RESOURCES(404, "请求资源不存在"),
    ERROR_SERVER(503, "系统异常"),
    ERROR_NO_LOGIN(1010, "用户未登录"),
    ERROR_LOGIN_TIMEOUT(1011, "登录用户超时，请重新登录"),
    ERROR_USER_MORE(1012, "此用户已在其他客户端登录"),
    ERROR_USER_NONE(1009, "用户不存在"),
    ERROR_USER_PASSWORD(1008, "用户名或密码不正确"),
    ERROR_USER_NAME_EMPTY(1007, "用户名为空"),
    ERROR_USER_PASSWORD_ENPTY(1006, "密码为空"),
    ERROR_USER_NAME_PASSWORD_EMPTY(1005, "用户名为空;密码为空"),
    ERROR_USER_OLD_PASSWORD_EMPTY(1100, "旧密码不能为空"),
    ERROR_USER_NEW_PASSWORD_EMPTY(1101, "新密码不能为空"),
    ERROR_USER_NEW_PASSWORD_CONFIRM_EMPTY(1102, "确认新密码不能为空"),
    ERROR_USER_CONFIRM_PASSWORD_DIFF(1103, "新密码与确认新密码不一致"),
    ERROR_USER_OLD_PASSWORD_ERROR(1104, "旧密码错误，请重新输入"),
    ERROR_USER_PASSWORD_CONFIRM_PASSWORD_DIFF(1105, "密码与确认密码不一致"),
    ERROR_USER_REGED_LOGIN(1106, "该手机号已注册，请登录"),
    ERROR_USER_REG_COMMUNITY_EMPTY(1107, "注册园区不能为空"),
    ERROR_USER_LAST_COMMUNITY_EMPTY(1109, "最后登录园区不能为空"),
    ERROR_USER_COMMUNITY_NOT_EXIST(1110, "小区id不存在"),
    ERROR_USER_TYPE_ERROR(1111, "用户类型参数错误"),
    ERROR_USER_SHOP_AUTH_ERROR(1112, "用户与店铺归属错误"),
    ERROR_USER_SHOP_PRODUCT_ERROR(1113, "店铺与商品归属错误"),
    ERROR_DEVICE_NULL(1009, "设备编号为空"),
    ERROR_USER_AGENT(1013, "user-agent 请求参数错误"),
    ERROR_NO_SELLER(1014, "无法获取店铺信息"),
    ERROR_WX_AUTH_ERROR(1015, "微信授权失败"),
    ERROR_WX_CALLBACK_STATE_ERROR(1015, "微信授权state异常"),
    ERROR_WX_NO_APP_INFO(1015, "无法获取公众号信息"),
    ERROR_LOGIN_FAILURE(1015, "登录失败"),
    ERROR_REGIST_FAILURE(1015, "一键注册失败"),
    ERROR_VERIFYCODE(1015, "验证码不正确或验证码已过期"),
    ERROR_PHONE_MSG(1016, "手机验证码发送失败"),
    ERROR_PHONE_MSG_DAY_COUNT(1019, "请勿频繁发送手机验证码，每个手机号每天限制5次"),
    ERROR_PRODUCT_FAILURE(1017, "查询商品数据为空"),
    ERROR_UPDATE_FAILURE(1018, "数据不能更新"),
    ERROR_JW_FAILURE(1018, "数据不能更新"),
    ERROR_NO_RECORD(2001, "查询结果为空"),
    ERROR_PIC_UPLOAD_IO(2006, "图片上传网络异常"),
    ERROR_SERVER_PUSH_BAIDU_MSG(3001, "推送服务端发送异常"),
    ERROR_CLIENT_PUSH_BAIDU_MSG(3002, "推送客户端发送异常"),
    ERROR_NO_BIND_ACCOUNT(3003, "请先绑定第三方账号"),
    ERROR_PIC_UPLOAD_FILENAME_EMPTY(2007, "文件名为空或不规范"),
    ERROR_SHOP_COMMENT_COUNT_ERROR(5001, "很抱歉，每个订单只能评论一次"),
    ERROR_PHONE_NUMBER_ERROR(6001, "手机号格式不正确"),
    ERROR_SERVICE_PHONE_NUMBER_ERROR(7001, "客服电话不能超过4个"),;

    private static final Logger logger = Logger.getLogger(ReturnCode.class);

    private int code;
    private String msg;

    private ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ReturnCode codeToEnum(int code) {

        ReturnCode[] values = ReturnCode.values();
        for (ReturnCode returnCode : values) {
            if (returnCode.code == code) {
                return returnCode;
            }
        }
        return ACTIVE_EXCEPTION;
    }

    public int code() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String msg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, ?> Map() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", this.code);
        hashMap.put("msg", this.msg);
        logger.info(hashMap);
        return hashMap;
    }

    public Map<String, ?> Map(int code) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", code);
        hashMap.put("msg", this.msg);
        logger.info(hashMap);
        return hashMap;
    }

    public Map<String, ?> Map(Object msg) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", this.code);
        hashMap.put("msg", msg);
        logger.info(hashMap);
        return hashMap;
    }

    public Map<String, ?> Map(int code, Object msg) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", code);
        hashMap.put("msg", msg);
        logger.info(hashMap);
        return hashMap;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("{\"code\":").append(this.code).append(",");
        sb.append("\"msg\":\"").append(this.msg).append("\"}");

        logger.info(sb.toString());
        return sb.toString();
    }

}
