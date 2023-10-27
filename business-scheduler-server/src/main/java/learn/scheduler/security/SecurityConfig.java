package learn.scheduler.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authConfig) throws Exception {
        http.csrf().disable();

        http.cors();

        http.authorizeRequests()
                .antMatchers("/login", "/refresh_token").permitAll()
                .antMatchers("/refresh_token").authenticated()
                .antMatchers("/register").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/business/*", "/service/*", "/business/query/*", "/business/category/*",
                        "/notification/*", "/appointment/*", "/appointment/business/*").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/notification").hasAnyAuthority("USER", "OWNER", "ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/business", "/appointment", "/notification").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.POST,
                        "/service").hasAnyAuthority("OWNER", "ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/business/*", "/service/*", "/availability/*").hasAnyAuthority("OWNER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/notification/*", "/appointment/*").hasAnyAuthority("USER", "OWNER", "ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/business/*", "/service/*", "/availability/*", "/notification/*", "/appointment/*").hasAnyAuthority("OWNER", "ADMIN")
                .antMatchers("/**").denyAll()
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(authConfig), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
