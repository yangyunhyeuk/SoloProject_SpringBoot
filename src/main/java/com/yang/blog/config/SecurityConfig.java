package com.yang.blog.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 환경 조성


// 다음의 3개 어노테이션을 시큐리티의 기본 설정이다.
@Configurable // 빈등록(IoC 관리)
@EnableWebSecurity // 시큐리티 필터가 등록된다 == 스프링 시큐리티가 활성화가 되어있는데, 어떤 설정을 해당 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻이다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean // IoC == 함수가 리턴하는 값을 스프링이 관리한다.
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf 토큰 비활성화 (테스트 시 걸어놓는 게 좋다.)
                .authorizeRequests()
                .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/loginForm");
    }
}
