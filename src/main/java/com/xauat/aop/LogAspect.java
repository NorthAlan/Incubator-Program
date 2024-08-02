package com.xauat.aop;
import com.alibaba.fastjson.JSONObject;
import com.xauat.Pojo.operateLog;
import com.xauat.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect //切面类
public class LogAspect {
    @Autowired
    private com.xauat.Mapper.operateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest request;
    @Around("@annotation(com.xauat.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable{
        long begin = System.currentTimeMillis();
        Object result =joinPoint.proceed();
        long end = System.currentTimeMillis();
        //操作人ID
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");
        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        //操作类名
        String className = joinPoint.getTarget().getClass().getName();
        //操作方法名
        String methodName = joinPoint.getSignature().getName();
        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        //方法返回值
        String returnValue = JSONObject.toJSONString(result);
        //操作耗时
        Long costTime = end - begin;
        operateLog operateLog = new operateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);
        return result;
    }

}
