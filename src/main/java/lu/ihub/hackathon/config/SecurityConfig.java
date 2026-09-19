package lu.ihub.hackathon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**").permitAll() // Autoriser les ressources statiques
                        .anyRequest().authenticated() // Tout le reste nécessite d'être connecté
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true) // Rediriger vers la page d'accueil après connexion
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Utilisateur de test "en dur" pour avancer sur le hackathon
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("kyc_user")
                .password("password")
                .roles("COMPLIANCE_OFFICER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}