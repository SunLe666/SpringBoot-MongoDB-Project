package com.sunle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@MapperScan("com.sunle.mapper")
public class SpringbootMongodbProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongodbProjectApplication.class, args);
	}

}
