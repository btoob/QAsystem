package com.ryz.qasystem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryz.qasystem.model.RespBean;
import com.ryz.qasystem.model.User;
import com.ryz.qasystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()    //所有请求都要认证之后才能访问
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .successHandler((req, resp, authentication)->{
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    User user = (User) authentication.getPrincipal();
                    user.setPassword(null);
                    RespBean ok = RespBean.ok("登录成功", user);
                    String s = new ObjectMapper().writeValueAsString(ok);   //将这个user写成一个字符串

                    out.write(s);
                    out.flush();
                    out.close();

                })
                .failureHandler((res, resp, e)->{
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();

                    RespBean error = RespBean.error("登录失败");
                    //判断失败的原因
                    if(e instanceof LockedException){
                        error.setMsg("账户被锁定，请联系管理员");
                    }else if(e instanceof CredentialsExpiredException){
                        error.setMsg("密码过期，请联系管理员");
                    }else if(e instanceof AccountExpiredException){
                        error.setMsg("账户过期，请联系管理员");
                    }else if(e instanceof DisabledException){
                        error.setMsg("账户被禁用，请联系管理员");
                    }else if(e instanceof BadCredentialsException){
                        error.setMsg("用错误，请重新输入");
                    }

                    String s = new ObjectMapper().writeValueAsString(error);

                    out.write(s);
                    out.flush();
                    out.close();

                })
                .permitAll()   //表示登录相关的页面或者接口不要被拦截
                .and()
                .csrf().disable();
    }
}
