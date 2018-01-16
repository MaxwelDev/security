package edu.security.gp.config;

import edu.security.gp.security.GpUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private GpUserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                    .antMatchers("/projetos").hasAnyRole("PG_PROJETOS")
                    .antMatchers("/relatorio-equipe").hasAnyRole("PG_REL_EQUIPE")
                    .antMatchers("/relatorio-custos").hasAnyRole("PG_REL_CUSTOS")
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/entrar")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/entrar?logout")
                    .permitAll()
                .and()
                    .rememberMe()
                    .userDetailsService(userDetailsService);

             http
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    // Register HttpSessionEventPublisher
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }
}
