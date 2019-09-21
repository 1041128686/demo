package com.my.config;

import com.my.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/css/**","/js/**","/register","/register-page","/sendsms").permitAll()
                    .antMatchers("/editor/**","/editor").hasRole("ADMIN")
                    .antMatchers("/article/list").hasAnyRole("ADMIN","USER")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login-page").loginProcessingUrl("/login").failureForwardUrl("/login-error").successForwardUrl("/home")
                    .permitAll()
                    .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
                .and().csrf().disable()
                    .exceptionHandling().accessDeniedPage("/403");
    }


}
