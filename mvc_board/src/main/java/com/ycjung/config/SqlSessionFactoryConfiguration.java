/*package com.ycjung.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlSessionFactoryConfiguration {
    
    @Autowired
    DataSource dataSource;
    ApplicationContext applicationContext;

    @Bean
    public SqlSessionFactoryBean sessionBean() throws IOException {
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(dataSource);
        String location = "";
        ssfb.setMapperLocations(applicationContext.getResources(location));
        return ssfb;
    }
}
*/

//----------------- 테스트 용....