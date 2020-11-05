package com.deyuan.controller;

import com.deyuan.pojo.SysLog;
import com.deyuan.service.ISyslogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 孟哥
 * <p>
 * 2020/11/3
 */
@Component
/*@Aspect*/
public class LogAop {
    private Date visitTime;//访问时间
    private  Class claszz;
    private Method method;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISyslogService syslogService;




    //前置通知
    @Before("execution(* com.deyuan.controller.*.*(..))")
    public  void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //开始访问的时间
        visitTime=new Date();
        //据提访问的类
        claszz=jp.getTarget().getClass();
        //获取所有执行的方法名称
       String methodName= jp.getSignature().getName();
       //获取访问的方法参数
        Object[] args = jp.getArgs();
        if (args==null||args.length==0){
            method = claszz.getMethod(methodName);//只能获去无参方法
        }else {
            Class[] classArgs=new Class[args.length];
            for (int i=0;i<args.length;i++){
                classArgs[i] =args[i].getClass();
            }
            //封装参数
            claszz.getMethod(methodName,classArgs);
        }





    }
    //后置通知
    @After("execution(* com.deyuan.controller.*.*(..))")
    public void doAfter(){
        long time=new Date().getTime()-visitTime.getTime();//访问时常
        //获取操作的url（user/save）
        String url="";

        if (claszz != null && method !=null && claszz!=LogAop.class){
            //获取类上的requestMapping 注解里的内容
            RequestMapping claszzAnnotation = (RequestMapping) claszz.getAnnotation(RequestMapping.class);
            if (claszzAnnotation!=null){
                String[] classValue = claszzAnnotation.value();
                //获取方法上的requestMapping 注解里的内容
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                String[] methodValue = methodAnnotation.value();
                url=classValue[0]+methodValue[0];
                String ip = request.getRemoteAddr();//获取请求的ip地址
                //获取当前操作的用户
                SecurityContext context= SecurityContextHolder.getContext();
                //获取当前操作的用户
                User principal = (User) context.getAuthentication().getPrincipal();
                //获取用户名
                String username = principal.getUsername();
                SysLog sysLog=new SysLog();
                sysLog.setIp(ip);
                sysLog.setExecutionTime(time);
                sysLog.setMethod("[类名]"+claszz.getName()+"[方法名]"+method.getName());
                sysLog.setUrl(url);
                sysLog.setUsername(username);
                sysLog.setVisitTime(visitTime);
                syslogService.save(sysLog);
            }

        }




    }

}
