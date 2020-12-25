package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description
 * @Author v_liyichen
 * @date 2020.12.25 11:20
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());


//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encode = encoder.encode("123");
//        auth.inMemoryAuthentication().withUser("user").password(encode).roles("admin");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.formLogin()
               .loginPage("/login.html")
               .loginProcessingUrl("/user/login")
               .defaultSuccessUrl("/test/index").permitAll()
               .and().authorizeRequests()
               .antMatchers("/","/test/index","/user/login","/js/**","/css/**","/plugins/**").permitAll()
               .antMatchers("/test/findAll").hasAuthority("admin")
               .anyRequest().authenticated()
               .and().csrf().disable();
    }
}
