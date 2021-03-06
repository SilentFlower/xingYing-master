package com.xingying.shopping.master.config.security;

import com.xingying.shopping.master.common.filter.JwtAuthenticationTokenFilter;
import com.xingying.shopping.master.config.security.handler.AuthenticationEntryPointImpl;
import com.xingying.shopping.master.config.security.handler.AuthenticationFailHandlerImpl;
import com.xingying.shopping.master.config.security.handler.AuthenticationSuccessHandlerImpl;
import com.xingying.shopping.master.config.security.handler.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author SiletFlower
 * @date 2021/3/18 03:08:46
 * @description
 */
@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private com.xingying.shopping.master.config.security.handler.AccessDeniedHandlerImpl accessDeniedHandler;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationFailHandlerImpl authenticationFailHandler;
    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    /**
     * ??????????????????
     */
    @Override
    public void configure(WebSecurity webSecurity) {
        //?????????????????????,?????????????????????????????????
        webSecurity.ignoring().antMatchers(
                "/",
                "/css/**",
                "/js/**",
                "/images/**",
                "/layui/**"
        );
    }


    /**
     * http????????????
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //?????????????????? csrf ??????
        http.headers().frameOptions().disable();//?????? in a frame because it set 'X-Frame-Options' to 'DENY' ??????
        http.formLogin().failureHandler(authenticationFailHandler);
        http.formLogin().successHandler(authenticationSuccessHandler);
        http.logout().logoutSuccessHandler(logoutSuccessHandler);
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //http.anonymous().disable();
        http.authorizeRequests()
                .antMatchers("/master/login/**","/master/user/addUser","/master/user/loginWithGoogle").permitAll()//???????????????????????????
                //.antMatchers("/user").hasRole("ADMIN")  // user????????????ADMIN?????????????????????
//                .anyRequest()
//                .authenticated()// ?????????????????????URL?????????????????????????????????
                .anyRequest()
                .access("@rbacPermission.hasPermission(request, authentication)")//????????????????????????
                .and()
                .formLogin()
                .loginPage("/master/login")
                .loginProcessingUrl("/master/login")  //??????POST????????????
                .usernameParameter("username") //?????????????????????
                .passwordParameter("password") //??????????????????
                //???????????????
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler) //??????????????????
                .authenticationEntryPoint(authenticationEntryPoint)//????????????
                .and()
                .logout()
                .logoutUrl("/master/logout");
//                .logoutSuccessUrl("/master/logoutSuccess");  //??????????????????URL
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
    }
}
