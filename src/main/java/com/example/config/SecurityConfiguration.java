package com.example.config;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
@EnableWebSecurity()
@Configuration
public class SecurityConfiguration {

    @Resource
    UserMapper mapper;

    @Resource
    UserAuthService service;
    @Resource
    DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()   //首先需要配置哪些请求会被拦截，哪些请求必须具有什么角色才能访问
                .antMatchers("/static/**","/page/auth/**","/api/auth/**").permitAll()    //静态资源，使用permitAll来运行任何人访问（注意一定要放在前面）
                .antMatchers("/page/user/**","/api/user/**").hasRole("user")
                .antMatchers("/page/admin/**","api/admin/**").hasRole("admin")
                .anyRequest()
                .hasAnyRole("admin","user")
                .and()
                .userDetailsService(service)
                .formLogin()//配置Form表单登陆
                .loginPage("/page/auth/login")       //登陆页面地址（GET）
                .loginProcessingUrl("/api/auth/login")    //form表单提交地址（POST）
                .successHandler((request, response, authentication) -> {
                    HttpSession session=request.getSession();
                    User user=mapper.getPasswordByUsername(authentication.getName());
                    session.setAttribute("user",user);
                    if (user.getRole().equals("admin"))
                        response.sendRedirect("/page/admin/index");
                    else
                        response.sendRedirect("/page/user/index");
                })
                //.defaultSuccessUrl("/index",true)    //登陆成功后跳转的页面，也可以通过Handler实现高度自定义
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")    //退出登陆的请求地址
                .logoutSuccessUrl("/login")   //退出后重定向的地址
                .and()
                .rememberMe()
                .authenticationSuccessHandler((request, response, authentication) -> {
                    HttpSession session=request.getSession();
                    User user=mapper.getPasswordByUsername(authentication.getName());
                    session.setAttribute("user",user);
                    if (user.getRole().equals("admin"))
                        response.sendRedirect("/page/admin/index");
                    else
                        response.sendRedirect("/page/user/index");
                })
                .rememberMeParameter("remember")
                .tokenValiditySeconds(60 * 60 * 24 * 7)//设定过期时间
                .tokenRepository(new JdbcTokenRepositoryImpl(){{setDataSource(dataSource);}})
                .and()
                .csrf().disable();
        return httpSecurity.build();
    }
}

