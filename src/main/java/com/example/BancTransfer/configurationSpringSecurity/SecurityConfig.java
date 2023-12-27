package com.example.BancTransfer.configurationSpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {





    @Bean
    public SecurityFilterChain security(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                .requestMatchers("/save", "/get").permitAll()
                .requestMatchers("/api/transfer/update/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .cors().disable().csrf().disable()
                .httpBasic();


        return httpSecurity.build();

        //   httpSecurity.formLogin(Customizer.withDefaults());  //toate requesturile sa fie autentificate(autorizate)    // sa fie autentificarea prin form


    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
  /*  private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                .requestMatchers("/save").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .cors().disable().csrf().disable()
                .httpBasic();

        return httpSecurity.build();
    }
 */

}
