package com.ycjung.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ycjung.paging.PagingVo;

@Configuration
public class PagingConfiguration {
    
    @Bean
    public PagingVo setPagingBean() {
        
        return new PagingVo();
    }
}
