package org.hjjang.springexample.config.security;

import lombok.RequiredArgsConstructor;
import org.hjjang.springexample.auth.AuthProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProvider authProvider;

    private final AuthFailureHandler authFailureHandler;

    private final AuthSuccessHandler authSuccessHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**",
                "/rest/**",
                "/redis/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/secure/").permitAll()
                .antMatchers("/secure/**").access("ROLE_USER")
                .antMatchers("/secure/**").access("ROLE_ADMIN")
                .antMatchers("/secure/admin/**").access("ROLE_ADMIN")
                .antMatchers("/secure/**").authenticated()
            .and()
                .formLogin()
                .loginPage("/secure/user/login")
                .defaultSuccessUrl("/secure")
                .usernameParameter("id")
                .passwordParameter("password")
            .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/secure/user/logout"))
                .logoutSuccessUrl("/secure/")
                .invalidateHttpSession(true)
            .and()
                .csrf()
            .and()
                .authenticationProvider(authProvider);
        super.configure(http);
    }
}
