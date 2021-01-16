package com.zhanyd.app.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.zhanyd.app.common.ApiResult;
import com.zhanyd.app.common.util.JwtUtils;
import com.zhanyd.app.common.util.StringHelp;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;


/**
 * Created by zhanyd@sina.com on 2018/2/16 0016.
 */
public class PermissionInterceptor implements HandlerInterceptor {
    /**
     * 判断是否登录
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        System.out.println("in preHandle");
        String token = request.getHeader("Authorization");
        System.out.println(request.getRequestURI());
        if(StringHelp.isEmpty(token)){
            responseMessage(response,"token不能为空");
            return false;
        }

        Integer result = JwtUtils.verifyJWT(token);
        if(result == null){
            responseMessage(response,"token无效或已过期");
            return false;
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }


    /**
     * 发送消息给客户端
     * @param response
     * @param code
     * @param msg
     * @throws IOException
     */
    private void responseMessage(HttpServletResponse response,String msg) throws IOException
    {
        ApiResult<String> apiResult = new ApiResult<String>();
        String message = JSON.toJSONString(apiResult.tokenExpired(msg));
        response.setHeader("Content-type", "application/json");
        OutputStream stream = response.getOutputStream();
        stream.write(message.getBytes("UTF-8"));
        stream.flush();
        stream.close();
    }


}
