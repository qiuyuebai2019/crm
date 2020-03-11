package com.qust.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qust.domain.Employee;
import com.qust.domain.Systemlog;
import com.qust.mapper.SystemlogMapper;
import com.qust.util.RequestUtil;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年06月03日 09时53分
 */

//① 在类上使用 @Component 注解 把切面类加入到IOC容器中
//② 在类上使用 @Aspect 注解 使之成为切面类
@Aspect
@Component
public class SystemAspect {

    @Autowired
    private SystemlogMapper systemlogMapper;

    /**
     * 定义切入点，切入点为com.example.aop下的所有函数
     */
    @Pointcut("execution(public * com.qust.service.*.*(..))")
    public void writeLog(){

    }

    /*
     * 通过连接点切入
     */
    @After("writeLog()")
    public void sayAfter(JoinPoint joinPoint) throws JsonProcessingException {
        writeLog();
        //设置时间
        Systemlog systemlog = new Systemlog();
        systemlog.setOptime(new Date());
        //设置ip地址
        HttpServletRequest request = RequestUtil.getRequest();
        if(request !=null){
            String IP = request.getRemoteAddr();
            systemlog.setIp(IP);
        }
        //获得目标执行方发的全路径:获取方法名称
        String name = joinPoint.getTarget().getClass().getName()+":"+joinPoint.getSignature().getName();
        systemlog.setFunction(name);
        //获取方法参数
        String params = new ObjectMapper().writeValueAsString(joinPoint.getArgs());
        systemlog.setParams(params);
        //获取id
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee)subject.getPrincipal();
        systemlog.setUserid(employee.getId());
        systemlogMapper.saveLog(systemlog);
    }



}
