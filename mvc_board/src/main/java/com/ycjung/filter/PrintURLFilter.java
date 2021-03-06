package com.ycjung.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ycjung.config.PagingConfiguration;

public class PrintURLFilter implements Filter{
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
        System.out.println("init()..");
        System.out.println("Spring Version: "+org.springframework.core.SpringVersion.getVersion()); // 스프링 버전
        
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(PagingConfiguration.class);
        ctx.refresh();
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest req = (HttpServletRequest) request;
        request.setCharacterEncoding("utf-8"); // encoding
        System.out.println("요청 URL: " + req.getRequestURI() + " --------------------------------");
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        System.out.println("destroy()..");
    }
}
