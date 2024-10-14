//package com.example.eventhub.jwt;
//
//import com.example.eventhub.dto.response.UserDto;
//import com.example.eventhub.entity.User;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import org.springframework.security.core.Authentication;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import java.security.Key;
//import java.time.Instant;
//import java.util.Date;
//
//import java.util.Map;
//
//@Slf4j
//@Component
//public class JwtService {
//
//    private Key key;
//
//    @Value("${security.jwt.secret}")
//    private String secret;
//
//    @Value("${security.jwt.duration}")
//    private long duration;
//
//    @PostConstruct
//    private void init() {
//        byte[] keyBytes = Decoders.BASE64.decode(secret);
//        key = Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String generateToken(UserDto userDto, List<Roles> roles) {
//        final JwtBuilder jwtBuilder = Jwts.builder()
//                .setIssuedAt(new Date())
//                .claim("id", userDto.getId())
//                .claim("email",userDto.getEmail())
//                .claim("roles",roles)    //for role
//                .setExpiration(Date.from(Instant.now().plusSeconds(duration)))
//                .setHeader(Map.of(Header.TYPE, Header.JWT_TYPE))
//                .signWith(key, SignatureAlgorithm.HS512);
//        return jwtBuilder.compact();
//    }
//
//    public String refreshToken(User userDto) {
//        final JwtBuilder jwtBuilder = Jwts.builder()
//                .setIssuedAt(new Date())
//                .claim("id", userDto.getId())
//                .setExpiration(Date.from(Instant.now().plusSeconds(duration)))
//                .setHeader(Map.of(Header.TYPE, Header.JWT_TYPE))
//                .signWith(key, SignatureAlgorithm.HS512);
//        return jwtBuilder.compact();
//    }
//
//    public Authentication  validateAccessToken(String acssessToken) {
//        Claims claims = parseToken(acssessToken);
//
//        if(claims.getExpiration().before(new Date())) {
//            log.info("JWT exp date is expired");
//            throw new JwtException("Expired or invalid JWT token");
//        }
//        Long id =    claims.get("id", Long.class);
//        String email = (String) claims.get("email");
//        List<String> rolesList = (claims.get("roles", List.class));
//
//        UserDto userDto = new UserDto();
//        userDto.setId(id);
//        userDto.setEmail(email);
//
//        final List<GrantedAuthority> authorities = new ArrayList<>();
//        rolesList.forEach(roles ->{
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + roles));
//        });
//
//
//        Authentication authentication =
//                new UsernamePasswordAuthenticationToken(userDto, "",authorities); //aAuthentication obyektini yaradir
//
//        return authentication;
//    }
//
//    private Claims parseToken(String accessToken) {     // accses tokenin bizim key le genarete olundugunu yoxlayiriq
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(accessToken)
//                .getBody();
//    }
//
//    public Long  validateRefreshToken(String refreshToken) {
//
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(refreshToken)
//                .getBody();
//        if(claims.getExpiration().before(new Date())) {
//            log.info("JWT exp date is expired");
//            throw new JwtException("Expired or invalid JWT token");
//        }
//
//        return  claims.get("id", Long.class);
//
//
//    }
//
//}
