package com.xauat.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.xauat.Pojo.Result;
import com.xauat.Utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {//登录校验拦截器
    @Override//该方法是在执行目标资源方法之前调用的
    // 拦截到请求后，会调用该方法，如果该方法为true，则放行该请求进入controller，如果为false则阻止进入controller
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //3.获取请求头中的令牌
        String jwt = request.getHeader("token");
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头为空，没有令牌，禁止访问web资源(没有登陆成功的标记)");
            Result error = Result.error("NOT_LOGIN");//告诉前端这人没登陆。禁止访问。
            //在controller中会自动将对象转换为json数据返回给前端，在过滤器中得手动设置
            //使用fastjson工具包
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);//在没有令牌的时候，过滤器将返回给前端error信息。
            return false;
        }
        //4.有令牌，解析令牌。
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//解析失败
            e.printStackTrace();
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);//返回错误信息
            return false;
        }
        //5.令牌合法，放行
        return true;
    }
    @Override//在目标资源方法执行完以后调用
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override//在视图渲染完毕后调用，是最后调用的
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

