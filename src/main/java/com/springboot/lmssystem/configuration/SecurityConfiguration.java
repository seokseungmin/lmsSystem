package com.springboot.lmssystem.configuration;

import com.springboot.lmssystem.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    private final MemberService memberService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/", "/member/register", "/member/email-auth", "/member/find-password", "/member/reset/password").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
        );

        http.formLogin(formLogin ->
                formLogin
                        .loginPage("/member/login")
                        .failureHandler(getFailureHandler()) // 여기서 핸들러를 설정
                        .permitAll()
        );

        http.logout(logout ->
                logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
        );

        http.exceptionHandling(exceptionHandling ->
                exceptionHandling.accessDeniedPage("/error/denied")
        );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // @Bean 제거, 단순히 설정용 메서드로 사용
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(getPasswordEncoder());
    }
}