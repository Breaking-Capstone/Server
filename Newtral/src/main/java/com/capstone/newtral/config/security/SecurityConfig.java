package com.capstone.newtral.config.security;

import com.capstone.newtral.config.exception.ClientAccessDeniedException;
import com.capstone.newtral.config.exception.ClientEntryPointException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final JwtProvider jwtProvider;

    @Autowired
    public SecurityConfig(JwtProvider jwtProvider){
        this.jwtProvider = jwtProvider;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.httpBasic().disable()

                .csrf().disable()

                .sessionManagement()
                .sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/users/*", "topics/*","interests/*").permitAll()
                .antMatchers("**exception**").permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(new ClientAccessDeniedException())
                .and()
                .exceptionHandling().authenticationEntryPoint(new ClientEntryPointException())

                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider),
                        UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/v3/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/**", "/swagger-ui/**", "/webjars/**");
    }
}
