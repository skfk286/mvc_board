package com.ycjung.filter;

import static com.ycjung.constants.DEFAULT.LOG_KEY;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ycjung.config.PagingConfiguration;

public class PrintURLFilter implements Filter{
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("{} filter init()", LOG_KEY);
        logger.debug("{} Spring Version: {}", LOG_KEY, org.springframework.core.SpringVersion.getVersion());
        
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(PagingConfiguration.class);
        ctx.refresh();
        ctx.close();
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        request.setCharacterEncoding("utf-8"); // encoding
        
        logger.debug("{} Request URL: {} ", LOG_KEY, req.getRequestURI());
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        logger.debug("{} Filter Destory", LOG_KEY);
    }
}
