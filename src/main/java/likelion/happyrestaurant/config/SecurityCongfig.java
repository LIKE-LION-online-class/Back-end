package likelion.happyrestaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityCongfig {

    private final String[] PUBLIC_ENDPOINTS = {"/users/**", "/auth/**"};


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests(request ->
//                request.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll()
////                        .requestMatchers(HttpMethod.GET,"/users/**").hasAuthority("ADMIN")
//                        .anyRequest().authenticated());
//        httpSecurity.oauth2ResourceServer(oauth2 ->
//                oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(this::decode)
//                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
//                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
//        );
//        httpSecurity.csrf(AbstractHttpConfigurer::disable);
//        return httpSecurity.build();
//    }

//    @Bean
//    JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//        return jwtAuthenticationConverter;
//    }
//
//    private Jwt decode(String token) throws JwtException {
//        try {
//            SignedJWT signedJWT = SignedJWT.parse(token);
//            return new Jwt(token,
//                    signedJWT.getJWTClaimsSet().getIssueTime().toInstant(),
//                    signedJWT.getJWTClaimsSet().getExpirationTime().toInstant(),
//                    signedJWT.getHeader().toJSONObject(),
//                    signedJWT.getJWTClaimsSet().getClaims()
//            );
//        } catch (ParseException e) {
//            throw new JwtException("Invalid token");
//        }
//    }

//    @Bean
//    JwtDecoder jwtDecoder() {
//        SecretKeySpec secretKeySpec = new SecretKeySpec(signKey.getBytes(), "HS512");
//        return NimbusJwtDecoder
//                .withSecretKey(secretKeySpec)
//                .macAlgorithm(MacAlgorithm.HS512)
//                .build();
//    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
