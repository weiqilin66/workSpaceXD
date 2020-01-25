package com.lwq.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description:
 * @author: LinWeiQi
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true) // 可以在方法上配置权限 不只controller
public class MultiHttpSecurityConfig {

    // springSecurity要求必须加密
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        // 不加密
//      return NoOpPasswordEncoder.getInstance();
    }
    // 公共配置
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                //.withUser("wayne").password("123").roles("admin") //123被加密成如下代码
                .withUser("wayne").password("$2a$10$lgqCrFZRustUo5RLAjQxcu7tabFo9RFSBhNZ/zCaLO6E8lziMfuDK").roles("admin")
                .and()
                .withUser("wayne2").password("$2a$10$lgqCrFZRustUo5RLAjQxcu7tabFo9RFSBhNZ/zCaLO6E8lziMfuDK").roles("user");
    }

    /**
     * @TODO   多httpSecurity配置要指定顺序
     * @date   2020/1/19
     */
    @Configuration
    @Order(1)   // 越小优先级越高 不加默认优先级最低
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**").authorizeRequests().
                    anyRequest().hasAnyRole("admin");
        }
    }

    @Configuration
    public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginProcessingUrl("/doLogin")
                    .permitAll()
                    .and()
                    .csrf().disable();
        }
    }
}
