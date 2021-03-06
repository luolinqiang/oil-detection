package com.oil.detection.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jd.common.web.cookie.CookieUtils;
import com.oil.detection.common.CommonConstants;
import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created with IntelliJ IDEA.
 * User: guowenjuan
 * Date: 15-11-9
 * Time: 下午5:58
 * To change this template use File | Settings | File Templates.
 */
public class TicketRequiredInterceptor extends AbstractInterceptor {

    @Resource
    protected CookieUtils cookieUtils;

    @Override
    public final boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        String cookieValue = cookieUtils.getCookieValue(request, CommonConstants.OIL_DETECTION_PIN_COOKIE);
        if (null == cookieValue) {
            boolean isAjax = ((null == request.getHeader("X-Requested-With")) ? false : true);
            if (!isAjax) {
                String redirectUrl = request.getRequestURI();
                response.sendRedirect("/u/login?redirectUrl=" + redirectUrl);
            } else {
                ResponsesDTO responsesDTO = new ResponsesDTO(ReturnCode.ERROR_NO_LOGIN);
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(JSONObject.toJSONString(responsesDTO));
                response.getWriter().flush();
            }
            return false;
        }
        return true;
    }
}
