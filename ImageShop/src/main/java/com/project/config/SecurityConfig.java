package com.project.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.project.common.sercurity.CustomAccessDeniedHandler;
import com.project.common.sercurity.CustomLoginSuccessHandler;
import com.project.common.sercurity.CustomNoOpPasswordEncoder;
import com.project.common.sercurity.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration

@EnableWebSecurity

//시큐리티 애너테이션 활성화를 위한 설정
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

	@Autowired
	DataSource dataSource;

	// 객체화
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("SecurityConfig");

		// 1. csrf 토큰 비활성화
		http.csrf().disable();

		// 2. 모든사이트에 인증이 되면 모든 인가가 된 상태가 기본이므로 막을 방법이 필요
		// 2. 인가 설정 : 팀원이 각자 결정
//		http.authorizeRequests().requestMatchers("/board/**").authenticated();
//		http.authorizeRequests().requestMatchers("/manager/**").hasRole("MANAGER");
//		http.authorizeRequests().requestMatchers("/admin/**").hasRole("ADMIN");
//		http.authorizeRequests().anyRequest().permitAll();

		// 2-1. Security 내 로그인 기본 폼 지정 한 뒤 로그인 성공 후 처리를 담당하는 지정 핸들러 호출.
		http.formLogin().loginPage("/auth/login").loginProcessingUrl("/login")
				.successHandler(createAuthenticationSuccessHandler());

		// 3. id 와 패스워드가 잘못 되었을 때
		http.exceptionHandling().accessDeniedHandler(createAccessDeniedHandler());

		http.rememberMe().key("zeus").tokenRepository(createJDBCRepository()).tokenValiditySeconds(60 * 60 * 24);

		// 로그아웃
		http.logout().logoutUrl("/auth/logout").invalidateHttpSession(true).deleteCookies("rememberme", "JSESSION_ID");

		// SecurityFilterChain 를 http.build()로 리턴
		return http.build();

	}

	// ReadMe 기능
	private PersistentTokenRepository createJDBCRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	// 지정된 아이디와 패스워드로 로그인이 가능하도록 설정한다.
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(createUserDetailsService()).passwordEncoder(createPasswordEncoder());
	}

	@Bean
	public UserDetailsService createUserDetailsService() {
		return new CustomUserDetailsService();
	}

	// 사용자가 정의한 비밀번호 암호화 처리기를 빈으로 등록한다.
	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 로그인이 정상적으로 진행 되었을 때 발동하는 핸들러
	@Bean
	public AuthenticationSuccessHandler createAuthenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}

	// CustomAccessDeniedHandler를 빈으로 등록한다.
	// 로그인이 정상적으로 이루어지지 않았을 때 발동하는 핸들러
	@Bean
	public AccessDeniedHandler createAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
}
