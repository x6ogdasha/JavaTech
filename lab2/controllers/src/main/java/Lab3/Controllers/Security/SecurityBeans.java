package Lab3.Controllers.Security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityBeans {

    @Bean
    SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .requestMatchers("/permit-all").permitAll()
                        .requestMatchers("/deny-all").denyAll()
                        .requestMatchers("/anonymous").anonymous()
                        .requestMatchers("/authenticated").authenticated()
                        .requestMatchers("/remember-me").rememberMe()
                        .requestMatchers("/fully-authenticated").fullyAuthenticated()
                        .requestMatchers("/has-view-authority").hasAuthority("view")
                        .requestMatchers("/has-update-or-delete-authority").hasAnyAuthority("update","delete")
                        .requestMatchers("/has-admin-role").hasRole("admin")
                        .requestMatchers("/has-access").access(((authentication, object) ->
                                new AuthorizationDecision("c.norris".equals(authentication.get().getName()))))
                        .anyRequest().denyAll()

                )
                .build();
    }
}
