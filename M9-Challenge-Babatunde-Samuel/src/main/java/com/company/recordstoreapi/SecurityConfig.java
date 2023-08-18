package com.company.recordstoreapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    private final String USERS_AUTH_USER = "SELECT\n" +
            "username,\n" +
            "password,\n" +
            "enabled\n" +
            "FROM users\n" +
            "WHERE\n" +
            "username =?";

    private final String AUTHORITY_AUTH_USER = "SELECT\n" +
            "username,\n" +
            "password,\n" +
            "enabled\n" +
            "FROM authorities\n" +
            "WHERE \n" +
            "username =?";

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authorities where username = ?")
                .passwordEncoder(encoder);

    }

    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.httpBasic();

        httpSecurity.authorizeHttpRequests()
                .mvcMatchers(HttpMethod.GET,"/records").authenticated()
                .mvcMatchers(HttpMethod.GET,"/records/{id}").authenticated()
                .mvcMatchers(HttpMethod.POST,"/records").authenticated()
                .mvcMatchers(HttpMethod.PUT, "/records/{id}").authenticated()
                .mvcMatchers(HttpMethod.DELETE, "/records/{id}").authenticated();

        httpSecurity.logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/home")
                .deleteCookies("JSESSIONID")
                .deleteCookies("XSRF-TOKEN")
                .invalidateHttpSession(true);

        httpSecurity
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
}
