package com.oil.detection.web.interceptor;

import com.jd.common.web.cookie.CookieUtils;
import com.oil.detection.common.CommonConstants;

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
            response.sendRedirect("/u/login");
            return false;
        }
        return true;
    }
}
