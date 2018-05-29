package org.spring.springboot.Interceptor;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.util.BaseResponse;
import org.spring.springboot.util.DateUtil;
import org.spring.springboot.util.JsonUtil;
import org.spring.springboot.util.StringUtil;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.reflect.generics.tree.ReturnType;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

/**
 * 拦截器一    HandlerInterceptorAdapter
 * Created by nice on 2018/3/15.
 */
@Slf4j
public class MyInterceptor1 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //是这个路径就直接放过
        if ("/report/post".equals(httpServletRequest.getRequestURI())){
            log.info(DateUtil.formatDate(new Date(), DateUtil.YEAR_MONTH_DAY_HH_MM_SS) + " MyInterceptor1  preHandle  time ");
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setReturnCode(1000);
        baseResponse.setMessage("成功");
        httpServletRequest.setAttribute("_ResResult",baseResponse);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        log.info(DateUtil.formatDate(new Date(), DateUtil.YEAR_MONTH_DAY_HH_MM_SS) + " >>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
        //是否已经响应写入
        if (response.isCommitted()) {
            return;
        }
//        if (handler.getClass().equals(HandlerMethod.class)) {
//            //接口方法信息
//            HandlerMethod handlermethod = (HandlerMethod) handler;
//            //标准报文已经写入：是|否
//            Boolean isWrittenFlag = (Boolean) request.getAttribute("RESPONSE_RESULT_STANDARD_WRITTEN_FLAG");
//            //是否忽略标准结果：是|否
//            Boolean ignoreStdResult = (Boolean) request.getAttribute("IGNORE_STD_RESULT");
//            //如果ResponseBody类型的请求
//            if (handlermethod.getMethodAnnotation(ResponseBody.class) != null || handlermethod.getBeanType().getAnnotation(RestController.class) != null) {
//                if (!Boolean.TRUE.equals(isWrittenFlag) && !Boolean.TRUE.equals(ignoreStdResult)) {
//                    BaseResponse baseResponse=new BaseResponse();
//                    if (null!=handler){
//                        baseResponse.setReturnCode(1000);
//                        baseResponse.setMessage("成功");
//                        baseResponse.setDataInfo(handler);
//                    }
//                    String jsonResResult = JsonUtil.objectToJson(baseResponse);
//                    try {
//                        response.setContentType("application/json;charset=UTF-8");
//                        ServletOutputStream out = response.getOutputStream();
//                        out.print(jsonResResult);
//                        out.close();
//                        if (log.isDebugEnabled()) {
//                            log.debug("■[ResponseBody]：" + jsonResResult);
//                        }
//                    } catch (Exception e1) {
//                        log.error("接口异常，响应带Exception的ResResult信息时出错！", e1);
//                        ServletOutputStream outstream = response.getOutputStream();
//                        if (StringUtil.isNotEmpty(jsonResResult))
//                        outstream.write(jsonResResult.getBytes());
//                        log.info("接口异常，响应带Exception的ResResult信息，重试后成功。 ");
//                        if (log.isDebugEnabled()) {
//                            log.debug("■[ResponseBody]：" + jsonResResult);
//                        }
//                    }
//
//                }
//            }
//        }

    }
}
