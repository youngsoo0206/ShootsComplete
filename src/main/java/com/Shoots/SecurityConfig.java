package com.Shoots;


import com.Shoots.security.CustomAccessDeniedHandler;
import com.Shoots.security.CustomUserDetailsService;
import com.Shoots.security.LoginFailHandler;
import com.Shoots.security.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private DataSource dataSource;
    private LoginSuccessHandler loginSuccessHandler;
    private LoginFailHandler loginFailHandler;
    private CustomUserDetailsService customUserDetailsService;
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(DataSource dataSource, LoginSuccessHandler loginSuccessHandler, LoginFailHandler loginFailHandler, CustomUserDetailsService customUserDetailsService, CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.dataSource = dataSource;
        this.loginSuccessHandler = loginSuccessHandler;
        this.loginFailHandler = loginFailHandler;
        this.customUserDetailsService = customUserDetailsService;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        //영구 토큰 저장/인증 하는 클래스. 주로 '아이디 기억하기' 기능 등에 사용됨.
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource); // import javax.sql.DataSource
        return jdbcTokenRepository;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin((fo) -> fo.loginPage("/login")
                .loginProcessingUrl("/loginProcess")
                .usernameParameter("id")
                .passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailHandler));

        http.logout((lo)->lo.logoutSuccessUrl("/login")
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("remember-me", "JSESSION_ID"));

//        http.rememberMe((me)->me.rememberMeParameter("remember-me")
//                .userDetailsService(customUserDetailsService)
//                .tokenValiditySeconds(2419200)
//                .tokenRepository(tokenRepository()));


//        http.authorizeHttpRequests( //권한주는 방법, 밑에 보고 참조해서 다시 만들기
//                (au)->au
//                        .requestMatchers("/member/list","/member/info","/member/delete")
//                        .hasAuthority("ROLE_ADMIN")
//                        .requestMatchers("/member/update")
//                        .hasAnyAuthority("ROLE_ADMIN","ROLE_MEMBER")
//                        .requestMatchers("/board/**","/comment/**")
//                        .hasAnyAuthority("ROLE_ADMIN","ROLE_MEMBER")
//                        .requestMatchers("/**").permitAll()
//        );


        http.authorizeHttpRequests(
                (au)->au
                        .requestMatchers("/member/list","/member/info","/member/delete")
                        .hasAuthority("admin")
                        .requestMatchers("/member/update")
                        .hasAnyAuthority("admin", "common")
                        .requestMatchers("/board/**","/comment/**")
                        .hasAnyAuthority("admin", "common")
                        .requestMatchers("/**").permitAll()

        );

        http.exceptionHandling((ex)->ex.accessDeniedHandler(customAccessDeniedHandler));
        return http.build();
    }








}
