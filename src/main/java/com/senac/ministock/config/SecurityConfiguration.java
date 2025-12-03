package com.senac.ministock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UsuarioAuthenticationFilter usuarioAuthenticationFilter;

    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/api/usuario/email",
            "/api/usuario/criar",
            "/api/usuario/atualizar",
            "/api/usuario/atualizarStatus",
            "/api/usuario/listar",
            "/api/usuario/listarPorId",
            "/api/usuario/apagar",

            "/api/produto/atualizar/{id}",
            "/api/produto/criar",
            "/api/produto/atualizarStatus",
            "/api/produto/listar",
            "/api/produto/listarPorId",
            "/api/produto/apagar/{id}",

            "/api/notificacao/atualizar",
            "/api/notificacao/criar",
            "/api/notificacao/marcarComoLida",
            "/api/notificacao/atualizarStatus",
            "/api/notificacao/listar",
            "/api/notificacao/listarPorNotificacaoId",
            "/api/notificacao/apagar",

            "/api/movimentacoes_estoque/atualizar",
            "/api/movimentacoes_estoque/criar",
            "/api/movimentacoes_estoque/listar",
            "/api/movimentacoes_estoque/apagar",

            "/api/configuracao/atualizar",
            "/api/configuracao/criar",
            "/api/configuracao/atualizarStatus",
            "/api/configuracao/listar",
            "/api/configuracao/listarPorId",
            "/api/configuracao/apagar",

            "/api/categoria_produto/atualizar",
            "/api/categoria_produto/criar",
            "/api/categoria_produto/atualizarStatus",
            "/api/categoria_produto/listar",
            "/api/categoria_produto/listarPorId",
            "/api/categoria_produto/apagar",



            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    // Endpoints que requerem autenticação para serem acessados
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {



    };

    // Endpoints que só podem ser acessador por usuários com permissão de cliente
    public static final String [] ENDPOINTS_CUSTOMER = {
            "/jogo"
    };

    // Endpoints que só podem ser acessador por usuários com permissão de administrador
    public static final String [] ENDPOINTS_ADMIN = {
            "/categoria"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR")
                        .requestMatchers(ENDPOINTS_CUSTOMER).hasRole("CUSTOMER")
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                        .anyRequest().denyAll()
                )
                .addFilterBefore(usuarioAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
