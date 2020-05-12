package com.cloud.platform.producer.security.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.cloud.platform.producer.security.ErrorCodeConst;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is
 * found.
 */
public class JWTFilter extends GenericFilterBean {

    private TokenProvider tokenProvider;

    public JWTFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        try {
            if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
                Authentication authentication = this.tokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }catch (JwtException ex)
        {
            logger.warn("request url" +  httpServletRequest.getRequestURI());
            logger.warn("request context path " + httpServletRequest.getContextPath());
            HttpServletResponse response = (HttpServletResponse)servletResponse;
            response.setStatus(HttpStatus.OK.value());
            Long status = 500L;
            String error = "内部错误";
            if (ex instanceof ExpiredJwtException)
            {
               status = ErrorCodeConst.TOkenError;
               error = "令牌过期";
            }
            else  if ( ex instanceof SignatureException)
            {
                status = ErrorCodeConst.TOkenError;
                error ="令牌签名不对";
            }
            else if (ex instanceof MalformedJwtException)
            {
                status = ErrorCodeConst.TOkenError;
                error = "令牌格式不对";
            }
            else if (ex instanceof UnsupportedJwtException)
            {
                status = ErrorCodeConst.TOkenError;
                error  ="不支持的令牌类型";
            }
            
			/*
			 * PlatformResult platformResult = PlatformResult.builder().status(status)
			 * .message(error).timestamp(new Date().getTime()).build();
			 */
            response.setCharacterEncoding("UTF-8");
            Map<String,String> map = new HashMap<String,String>();
            map.put("error", error);
            map.put("status", status+"");
            String result = new ObjectMapper().writeValueAsString(map);
            response.getWriter().write(result);
        }
    }

    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(JWTConfigurer.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
