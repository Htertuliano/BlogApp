package org.example.rest.api;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http.authorizeRequests((authorize) ->
                        authorize.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    public UserDetailsService userDetailsService () {
        UserDetails haqq = User.builder()
                .username("htertu")
                .password("442211")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("admin")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(haqq, admin);
    }

}
