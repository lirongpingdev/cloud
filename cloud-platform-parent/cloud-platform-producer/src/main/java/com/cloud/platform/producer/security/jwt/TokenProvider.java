package com.cloud.platform.producer.security.jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.cloud.platform.producer.security.UserContext;
import com.cloud.platform.producer.security.UserType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String REAL_NAME = "name";
    private static final String USER_TYPE ="usertype";
    private static final String REAL_USERID = "userid";
    private static final String MOBILE = "mobile";
    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    @Autowired
    private Environment env;
    private String secretKey;

    private long tokenValidityInMilliseconds;

    private long tokenValidityInMillisecondsForRememberMe;

    @PostConstruct
    public void init() {
        this.secretKey = env.getProperty("jhipster.security.authentication.jwt.secret");
        this.tokenValidityInMilliseconds = 1000 * Integer.valueOf(env.getProperty("jhipster.security.authentication.jwt.token-validity-in-seconds"));
        this.tokenValidityInMillisecondsForRememberMe = 1000 * Integer.valueOf(env.getProperty("jhipster.security.authentication.jwt.token-validity-in-seconds-for-remember-me"));
    }
    
    public String createToken(Authentication authentication, boolean rememberMe) {
	        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

            UserContext principal = (UserContext) authentication.getPrincipal();
            long now = (new Date()).getTime();
            Date validity;
            if (rememberMe) {
                validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
            } else {
                validity = new Date(now + this.tokenValidityInMilliseconds);
            }
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .claim(REAL_NAME, principal.getRealName())
                .claim(REAL_USERID, principal.getUserId())
                .claim(USER_TYPE,principal.getUserType() != null? principal.getUserType().name() : UserType.REGULAR)
                .claim(MOBILE, principal.getTelephone())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .compact();
    }
    
	public String createTmpToken(Authentication authentication, long tokenValidityInMilliseconds) {
	        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

            UserContext principal = (UserContext) authentication.getPrincipal();
            long now = (new Date()).getTime();
            Date validity = new Date(now + tokenValidityInMilliseconds);
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .claim(REAL_USERID, principal.getUserId())
                .claim(USER_TYPE, UserType.TMP_ROLE_USER)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());


        UserContext userContext = new UserContext(claims.getSubject(),"",authorities);
        userContext.setRealName((String) claims.get(REAL_NAME));
        userContext.setTelephone((String) claims.get(MOBILE));
        userContext.setUserId((String) claims.get(REAL_USERID));
        userContext.setUserType(claims.get(USER_TYPE) != null ? UserType.valueOf((String)claims.get(USER_TYPE)): UserType.REGULAR);

        return new UsernamePasswordAuthenticationToken(userContext, token, authorities);

    }

    boolean validateToken(String authToken) throws JwtException {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException ex) {
            log.warn("jwt exception");
            log.warn(ex.getMessage());
            throw  ex;
        }

    }
}
