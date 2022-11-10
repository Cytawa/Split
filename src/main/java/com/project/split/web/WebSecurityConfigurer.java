package com.project.split.web;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
// import
// org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// @EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfigurer // extends WebSecurityConfigurerAdapter
 {
  // private final UserDetailsService userDetailsService;

  /*  public WebSecurityConfigurer(@Qualifier("userDetailsService") final UserDetailsService userDetailsService) {
      this.userDetailsService = userDetailsService;
  }

  @Override
  protected UserDetailsService userDetailsService() {
      return userDetailsService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http
              .csrf().disable();*/
  /**
   * Everyone has access to create an account and to create a user. Only the admin can remove the
   * bill, user and expense. The rest of the queries can be accessed by logged in userst
   */
  /*   http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/").hasRole("ADMIN")
                  .antMatchers("/split/user/**", "/split/bill/save")
                  .permitAll()
                  .anyRequest().authenticated()
                  .and().formLogin()
                  .permitAll().and()
                  .logout().permitAll();
      }

  */
}
