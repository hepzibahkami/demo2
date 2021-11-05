package com.example.myprofile.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.myprofile.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    

    @Autowired
    private UserService userService;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    @Bean
    public UserDetailsService gDetailsService(){
        return new UserService();
    }
    
    @Bean
    public BCryptPasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(PasswordEncoder());
        return provider;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/add_profile","/update_profile","/index").authenticated()
        .antMatchers("/registeruser","/login").permitAll()
        .and()
        .formLogin().loginPage("/login").defaultSuccessUrl("/index")
        .successHandler(new AuthenticationSuccessHandler() {

            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws IOException, ServletException {
                UserDetails userDetails=(UserDetails) authentication.getPrincipal();
                String username1=userDetails.getUsername();
                log.info("User "+username1+" has logged in successfully");
                response.sendRedirect(request.getContextPath());
                
            }
            
        })
        .and()
        .logout()
        .logoutSuccessHandler(new LogoutSuccessHandler() {

            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws IOException, ServletException {
                UserDetails userDetails=(UserDetails) authentication.getPrincipal();
                String username1=userDetails.getUsername();
                log.info("User "+username1+" has logged out successfully");
                response.sendRedirect("login?logout");

                
            }
            
        })
        .and()
        .exceptionHandling().accessDeniedPage("/index?notauthorized")
        .and()
        .csrf().disable();
    }
            
    
}




    
    
