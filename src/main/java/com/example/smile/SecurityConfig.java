package com.example.smile;

import com.example.smile.member.login.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
// Spring Security 설정 클래스
public class SecurityConfig{

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomLoginAuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    // 패스워드 인코더 빈 설정
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // SecurityFilterChain 빈 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable) //csrf 비활성화
                .cors(AbstractHttpConfigurer::disable) //cors 비활성화
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/login").authenticated()//http 요청 인증 필요
                        .requestMatchers(new AntPathRequestMatcher("/member/**")).permitAll() //http 요청 허용
                        .anyRequest().permitAll())//인증 필요 url 제외 모두 요청 허용
                .addFilterBefore(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)// 필터 추가
                .exceptionHandling(config -> config
                        .authenticationEntryPoint(authenticationEntryPoint)//인증 진입점 설정
                        .accessDeniedHandler(accessDeniedHandler));//접근 거부 핸들러 설정


        return http.build();
    }

    @Bean
    // Ajax 요청을 처리하는데 사용되는 CustomAuthenticationFilter 필터 생성
    public CustomAuthenticationFilter ajaxAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();
        customAuthenticationFilter.setAuthenticationManager(authenticationManager());
        customAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);//인증 성공 핸들러 설정
        customAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);//인증 실패 핸들러 설정

        // **
        customAuthenticationFilter.setSecurityContextRepository(
                new DelegatingSecurityContextRepository(
                        new RequestAttributeSecurityContextRepository(),
                        new HttpSessionSecurityContextRepository()
                ));

        return customAuthenticationFilter;
    }

    // AuthenticationManager 빈 설정
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}