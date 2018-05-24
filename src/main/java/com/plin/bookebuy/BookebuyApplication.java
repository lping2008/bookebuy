package com.plin.bookebuy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages= {"com.plin.bookebuy.mapper"})
public class BookebuyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookebuyApplication.class, args);
	}
}
