package org.spring.springboot.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.util.DateUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 拦截器2
 * Created by nice on 2018/3/15.
 */
@Slf4j
public class MyInterceptor2 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //是这个路径就直接放过
        if ("/report/post".equals(httpServletRequest.getRequestURI())) {
            log.info(DateUtil.formatDate(new Date(), DateUtil.YEAR_MONTH_DAY_HH_MM_SS) + " MyInterceptor2  preHandle  time ");
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //    DateUtil.formatDate(new Date(),DateUtil.YEAR_MONTH_DAY_HH_MM_SS)
        log.info(DateUtil.formatDate(new Date(), DateUtil.YEAR_MONTH_DAY_HH_MM_SS) + " >>>MyInterceptor2>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info(DateUtil.formatDate(new Date(), DateUtil.YEAR_MONTH_DAY_HH_MM_SS) + " >>>MyInterceptor2>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
