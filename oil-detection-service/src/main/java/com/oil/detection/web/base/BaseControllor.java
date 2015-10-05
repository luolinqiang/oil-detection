package com.oil.detection.web.base;

import com.jd.common.web.cookie.CookieUtils;
import com.oil.detection.common.CommonConstants;
import com.oil.detection.common.ResponsesDTO;
import com.oil.detection.common.ReturnCode;
import com.oil.detection.dao.UserMapper;
import com.oil.detection.domain.User;
import com.oil.detection.log.RunLog;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseControllor extends GlobalControllor {
    @Resource
    protected CookieUtils cookieUtils;
    @Resource
    protected UserMapper userMapper;

    /**
     * 获取当前登录用户pin值
     *
     * @return
     */
    protected User getUserInfo(HttpServletRequest request) {
        String cookieValue = cookieUtils.getCookieValue(request, CommonConstants.OIL_DETECTION_PIN_COOKIE);
        RunLog.LOG.info("-----------------cookie-----------------" + cookieValue);
        User user = parseCookie(cookieValue);
        // b端用户获取SupplierId
        if (user != null && user.getUserType() == CommonConstants.User.USER_TYPE_B) {
            User userQ = new User();
            userQ.setId(user.getId());
            User userR = userMapper.getUser(userQ);
            if (userR != null) {
                user.setSupplierId(userR.getSupplierId());
            }
        }
        return user;
    }

    public static User parseCookie(String cookieValue) {
        RunLog.LOG.info(String.format("【BaseControllor】【cookieValue:%s】", cookieValue));
        if (StringUtils.isNotBlank(cookieValue)) {
            String[] splitArr = cookieValue.split("\\" + CommonConstants.SEPARATOR);
            User user = new User();

            if (splitArr != null && splitArr.length >= 4) {
                user.setUserId(splitArr[0]);
                user.setPhone(splitArr[0]);
                if (StringUtils.isNotBlank(splitArr[1]) && !"null".equalsIgnoreCase(splitArr[1])) {
                    user.setUserType(Integer.parseInt(splitArr[1]));
                }
                if (StringUtils.isNotBlank(splitArr[2]) && !"null".equalsIgnoreCase(splitArr[2])) {
                    user.setId(Long.parseLong(splitArr[2]));
                }
                user.setDevice(splitArr[3]);
                return user;
            }

            if (splitArr != null && splitArr.length >= 3) {
                user.setUserId(splitArr[0]);
                user.setPhone(splitArr[0]);
                if (StringUtils.isNotBlank(splitArr[1]) && !"null".equalsIgnoreCase(splitArr[1])) {
                    user.setUserType(Integer.parseInt(splitArr[1]));
                }
                if (StringUtils.isNotBlank(splitArr[2]) && !"null".equalsIgnoreCase(splitArr[2])) {
                    user.setId(Long.parseLong(splitArr[2]));
                }

                if (splitArr.length >= 4) {
                    user.setDevice(splitArr[3]);
                }
                return user;
            }
        }
        return null;
    }

    protected void setCookieRedis(HttpServletResponse response, User user, ResponsesDTO responsesDTO) {
        Integer userType = user.getUserType();
        String userId = user.getUserId();
        String device = user.getDevice();

        if (responsesDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {
            User userR = (User) responsesDTO.getData();
            String value = String.format(CommonConstants.COOKIE_FORMAT, userId, userType, userR.getId(), device);
            cookieUtils.setCookie(response, CommonConstants.OIL_DETECTION_PIN_COOKIE, value);
//            String key = String.format(CommonCacheImpl.KEY, SysConstants.REDIS_PREFIX, "cookie_" + userR.getUserType(), userR.getUserId());
//            commonCache.setex(key, value, Constants.REDIS_COOKIE_TIMEOUT);
        }
    }

    protected void removeCookieRedis(HttpServletRequest request, HttpServletResponse response) {
        cookieUtils.deleteCookie(response, CommonConstants.OIL_DETECTION_PIN_COOKIE);
        User user = this.getUserInfo(request);
        if (user != null) {
//            String key = String.format(CommonCacheImpl.KEY, SysConstants.REDIS_PREFIX, "cookie_"
//                    + user.getUserType(), user.getUserId());
//            commonCache.remove(key);
        }
    }
}
