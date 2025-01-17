package mysite.config.app;

import mysite.repository.UserRepository;
import mysite.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin((formLogin) -> {
                    formLogin
                            .loginPage("/user/login")
                            .loginProcessingUrl("/user/auth")
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/")
//                            .failureUrl("/user/login?result=fail")
                            .failureHandler((request,
                                             response,
                                             exception) -> {
                                // 여기에서 사용하는 dispatcher는 다음 필터에서 해당 컨트롤러와의 매핑을 지원함
                                // 여기에서 포워딩 하게 되면 그전 URL Method 그대로 사용하고, loginProcessingUrl는 POST방식만을 지원하여
                                // PostMapping이 컨트롤러에 있어야함.
                                request.setAttribute("result", "fail");
                                request.setAttribute("email", request.getParameter("email"));
                                request.getRequestDispatcher("/user/login").forward(request, response);
                            });
                })
                .logout(logout -> {
                    logout
                            .logoutUrl("/user/logout")
                            .logoutSuccessUrl("/");
                })
                .authorizeHttpRequests((authorizeRequests) -> {
                    authorizeRequests
                            .requestMatchers(new RegexRequestMatcher("^/user/update$", null)).authenticated()
                            .anyRequest().permitAll();
                })
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }
}
