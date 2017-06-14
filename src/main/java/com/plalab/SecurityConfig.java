//package com.plalab;
//
//import com.plalab.domain.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
///**
// * @DESC WebSecurityConfigurerAdapter는 웹 인증을 구현해둔 클래스
// */
//@SuppressWarnings("SpringJavaAutowiringInspection")
////@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        super.configure(auth);
////        auth.userDetailsService(userService).passwordEncoder(userService.passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        super.configure(http);
////        http
////                .cors().disable()
////                .authorizeRequests()                //요청에 대한 권한 처리
////                    .anyRequest().authenticated();   // 어떤 요청이든 권한을 요구.
//////                .and().formLogin();               //form 을 통한 로그인을 사용한다
////        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//}
