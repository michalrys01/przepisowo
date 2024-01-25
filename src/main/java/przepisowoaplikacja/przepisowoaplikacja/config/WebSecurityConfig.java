package przepisowoaplikacja.przepisowoaplikacja.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    // Bean do kodowania hasła za pomocą BCrypt
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // Bean do konfigurowania łańcucha filtrowania zabezpieczeń
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Konfiguracja reguł autoryzacji
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/register/**").permitAll()
                                .requestMatchers("/recipes/new").authenticated()
                                .requestMatchers("/recipes/**").permitAll()
                )
                // Konfiguracja logowania opartego na formularzu
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/",true) // Przekieruj na stronę główną po udanym zalogowaniu
                        .failureUrl("/login?error") // Przekieruj na stronę logowania z parametrem błędu po nieudanym logowaniu
                        .permitAll()
                        .permitAll()
                        // Wyłącz ochronę przed atakami CSRF....

                )
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers ->
                        headers
                                .frameOptions(frameOptions ->
                                        frameOptions
                                                .sameOrigin()
                                )
                );

        return http.build();
    }
}
