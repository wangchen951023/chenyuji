package com.chenyuji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@SpringBootApplication
@ComponentScan(basePackages = {"com.chenyuji.*"})
public class App extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
