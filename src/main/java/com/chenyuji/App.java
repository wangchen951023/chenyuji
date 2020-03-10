package com.chenyuji;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@SpringBootApplication
@ComponentScan(basePackages = {"com.chenyuji.*"})
@MapperScan("com.chenyuji.mapper")
@ServletComponentScan("com.chenyuji.filter")
public class App extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }

}
