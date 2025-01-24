package com.zeus.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.zeus.common.security.CustomAccessDeniedHandler;
import com.zeus.common.security.CustomLoginSuccessHandler;
import com.zeus.common.security.CustomNoOpPasswordEncoder;
import com.zeus.common.security.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration

//자체 제작한 설정으로 시큐리티를 돌리겠다는 의미
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true, securedEnabled=true)
public class SecurityConfig {

	@Autowired
	DataSource dataSource;

	// 객체화
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("SecurityConfig");

		// 1. csrf 토큰 비활성화a
		http.csrf().disable();

		// 2. 모든사이트에 인증이 되면 모든 인가가 된 상태가 기본이므로 막을 방법이 필요
		// 2. /board/list는 인증만하면 접근가능
		http.authorizeRequests().requestMatchers("/board/list").permitAll();
		http.authorizeRequests().requestMatchers("/board/register").hasRole("MEMBER");
		http.authorizeRequests().requestMatchers("/notice/list").permitAll();
		http.authorizeRequests().requestMatchers("/notice/register").hasRole("ADMIN");

		// 2-1. Security내 로그인 기본 폼 지정
		http.formLogin().loginPage("/login").successHandler(createAuthenticationSuccessHandler());

		// 3. id 와 패스워드가 잘못 되었을 때
//		http.exceptionHandling().accessDeniedPage("/accessError");
		http.exceptionHandling().accessDeniedHandler(createAccessDeniedHandler());

		// 로그아웃
		http.logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("rememberme", "JSESSION_ID");

		http.rememberMe().key("zeus").tokenRepository(createJDBCRepository()).tokenValiditySeconds(60 * 60 * 24);

		// SecurityFilterChain 를 http.build()로 리턴
		return http.build();

		// 4. id, password 기존것 그대로 사용하지 않고 내가 설계한 아이디, 비밀번호, 인가 정책을 세워 제시(내가 만든 테이블
		// 인증/인가)
		// 4. 내가 만들 방식으로 실행하길 요청

	}

	private PersistentTokenRepository createJDBCRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 지정된 아이디와 패스워드로 로그인이 가능하도록 설정한다.
		auth.userDetailsService(createUserDetailsService()).passwordEncoder(createPasswordEncoder());
	}

	@Bean
	public UserDetailsService createUserDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new CustomNoOpPasswordEncoder();
	}

	@Bean
	public AuthenticationSuccessHandler createAuthenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}

	// CustomAccessDeniedHandler를 빈으로 등록한다.
	@Bean
	public AccessDeniedHandler createAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
}
