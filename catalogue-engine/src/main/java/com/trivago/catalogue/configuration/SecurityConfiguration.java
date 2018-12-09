package com.trivago.catalogue.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private SecurityFilter securityFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().disable()
        .csrf().disable()
        .exceptionHandling().disable()
        .addFilterBefore(securityFilter, BasicAuthenticationFilter.class)
        .authorizeRequests().anyRequest().authenticated();
  }
}
