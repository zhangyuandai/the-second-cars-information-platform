package com.keshe.config;

import com.keshe.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("regist.html").setViewName("regist");
        registry.addViewController("login.html").setViewName("login");
        registry.addViewController("index.html").setViewName("index");
        registry.addViewController("login2.html").setViewName("login2");
        registry.addViewController("admin.html").setViewName("admin");
        registry.addViewController("admin1.html").setViewName("admin1");
        registry.addViewController("userList.html").setViewName("userList");
        registry.addViewController("useradd.html").setViewName("useradd");
        registry.addViewController("userupdate.html").setViewName("userupdate");
        registry.addViewController("geren.html").setViewName("geren");
        registry.addViewController("regist3.html").setViewName("regist3");
        registry.addViewController("carsList.html").setViewName("carsList");
        registry.addViewController("index_cars.html").setViewName("index_cars");
        registry.addViewController("index_user.html").setViewName("index_user");
        registry.addViewController("brandList.html").setViewName("brandList");
        registry.addViewController("brandadd.html").setViewName("brandadd");
        registry.addViewController("brandEdit.html").setViewName("brandEdit");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/*").excludePathPatterns("/", "/login","/login2","/index","/index.html","/defaultKaptcha","/zhuce","/handleLogin","/handleLogin2","/regist.html","/regist");

    }
}
