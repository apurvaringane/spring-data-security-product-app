package org.jspiders.springdatasecurityproductapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {
/*
    //authentication
    @Bean
    public InMemoryUserDetailsManager userDetails(){
        UserDetails apurva= User.builder()
                .username("apurva")
                .password("{noop}123")
                .roles("DEALER","VENDOR","CUSTOMER")
                .build();

        UserDetails venom=User.builder()
                .username("ankita")
                .password("{noop}456")
                .roles("VENDOR","CUSTOMER")
                .build();

        UserDetails joker=User.builder()
                .username("arpita")
                .password("{noop}789")
                .roles("CUSTOMER")
                .build();

        return new InMemoryUserDetailsManager(apurva,ankita,arpita);
    }
    */

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
    //authorization
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http.authorizeHttpRequests()
        http.authorizeHttpRequests(config->
                        config
                                .requestMatchers("/").hasAnyRole("DEALER","VENDOR","CUSTOMER")
                                .requestMatchers("/get-product-form").hasRole("DEALER")
                                .requestMatchers("/save-product").hasRole("DEALER")
                                .requestMatchers("/update/{id}").hasAnyRole("DEALER","VENDOR")
                                .requestMatchers("/updateproduct").hasAnyRole("DEALER","VENDOR")
                                .requestMatchers("/delete/{id}").hasRole("DEALER")
                                .requestMatchers("/search").permitAll()
                                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .exceptionHandling(config->
                        config.accessDeniedPage("/access-denied"));
        return http.build();
    }
}
