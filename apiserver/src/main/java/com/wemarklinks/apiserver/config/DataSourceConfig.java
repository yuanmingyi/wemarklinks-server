package com.wemarklinks.apiserver.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wemarklinks.factory.data.dao")
public class DataSourceConfig {

}
