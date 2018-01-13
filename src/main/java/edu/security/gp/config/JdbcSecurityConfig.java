package edu.security.gp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static edu.security.gp.utils.JdbcUtils.PERMISSOES_POR_GRUPO;
import static edu.security.gp.utils.JdbcUtils.PERMISSOES_POR_USUARIO;
import static edu.security.gp.utils.JdbcUtils.USUARIO_POR_LOGIN;

//@Configuration
public class JdbcSecurityConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder,
                                PasswordEncoder passwordEncoder,
                                DataSource dataSource) throws Exception {
        builder
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery(USUARIO_POR_LOGIN)
                .authoritiesByUsernameQuery(PERMISSOES_POR_USUARIO)
                .groupAuthoritiesByUsername(PERMISSOES_POR_GRUPO);
    }

}
