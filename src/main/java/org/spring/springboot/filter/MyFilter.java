package org.spring.springboot.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by nice on 2018/3/15.
 */
@Slf4j
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     *
     * @param servletRequest  可以换成 HttpServletRequest
     * @param servletResponse  可以换成   HttpServletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        String remoteAddr = request.getRemoteAddr();
        //获取默认编码
        String charset = Charset.defaultCharset().toString();
        log.info("this is remoteAddr  "+remoteAddr  +"  charset is  "+charset);
        //打印请求Url
        log.info("this is MyFilter,url :" + request.getRequestURI());
        String characterEncoding = request.getCharacterEncoding();
        log.info("request  CharacterEncoding  "+ characterEncoding);
        if (StringUtils.isEmpty(request.getParameter("token"))){
            response.setStatus(403);
            response.getOutputStream().println("token is empty");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }


}
