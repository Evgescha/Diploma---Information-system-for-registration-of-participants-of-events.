package com.hescha.accountingforeventparticipants.config;

import com.hescha.accountingforeventparticipants.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;


@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private ApplicationContext context;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        DefaultWebSecurityExpressionHandler handler =
                new DefaultWebSecurityExpressionHandler();
        handler.setApplicationContext(context);
        web.expressionHandler(handler);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
//        .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/registration", "/login", "/j _spring_security_check").anonymous()
                .antMatchers("/", "/css/**", "/js/**", "/img/**", "/**/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/")
                .loginProcessingUrl("/login")
                .failureUrl("/registration")
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID");

        http.headers().frameOptions().sameOrigin();
    }

}
