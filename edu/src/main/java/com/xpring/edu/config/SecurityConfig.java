package com.xpring.edu.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("SELECT username, password, enabled FROM User WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, role from User WHERE username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/", "/home", "/register", "/process_register", "/style/**", "/students", "/students/search", "/employees",
                        "/employees/search", "/students/class/{^[0-9]+$}/", "/classes")
                .permitAll()
                .antMatchers("/employees/{^[0-9]+$}/", "/students/{^[0-9]+$}/", "/classes/**", "/subjects/**",
                        "/exams/**", "/results/**", "/credentials/change", "/credentials/{^[a-zA-Z0-9]+$}/change", "/test", "/edit_test", "/edit_test/{^[a-zA-Z0-9]+$}")
                .hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                .antMatchers("/credentials/**", "/employees/new", "/employees/{^[0-9]+$}/edit", "/students/new",
                        "/students/{^[0-9]+$}/edit")
                .hasAnyAuthority("ROLE_ADMIN").anyRequest().authenticated().
                and().formLogin().permitAll()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .and().logout()
                .logoutSuccessUrl("/home")
                .permitAll();
    }
    
    
}