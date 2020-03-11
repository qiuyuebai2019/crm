package com.qust.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月03日 10时41分
 */
public class RequestUtil {

    public static ThreadLocal<HttpServletRequest> local = new ThreadLocal<>();

    public static HttpServletRequest getRequest(){
        return local.get();
    }

    public static void setRequest(HttpServletRequest request){
        local.set(request);
    }
}
