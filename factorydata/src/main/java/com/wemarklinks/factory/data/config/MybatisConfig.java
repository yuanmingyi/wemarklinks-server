package com.wemarklinks.factory.data.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.wemarklinks.factory.data.dao"})
public class MybatisConfig {

}
