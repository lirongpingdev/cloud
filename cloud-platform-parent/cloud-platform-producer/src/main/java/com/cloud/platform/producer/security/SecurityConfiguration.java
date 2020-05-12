package com.cloud.platform.producer.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cloud.platform.producer.security.jwt.JWTConfigurer;
import com.cloud.platform.producer.security.jwt.TokenProvider;

/**
 * 负责拦截接口
 * @author lrp
 *
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	public static final String AUTHENTICATION_HEADER_NAME = "Authorization";
    public static final String REFRESH_TOKEN_URL = "/api/auth/token";
    
    @Autowired
    private TokenProvider tokenProvider;
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        //不拦截--项目相关URL（公共部份）
        web.ignoring().antMatchers("/producercontext/product/login");//http://127.0.0.1:8002/producercontext/product/login
        web.ignoring().antMatchers("/product/login/**");
        web.ignoring().antMatchers("/shutdown");
        web.ignoring().antMatchers("//actuator/**");
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
                "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
    }
	
	/**
	 * 不拦截--项目相关URL--体具业务接口
	 */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> permitAllEndpointList = Arrays.asList(
                "/console", "/",
                "/webjars/**",
                "/static/**",
                "/failure.html",
                "/success.html",
                "/appFailure.html",
                "/appSuccess.html",
                "/swagger*/**",
                "/api/dynamic/**",
                "/api/public/**",
                "/health",
                "/api/dynamic/**",
                "/api/region/**",
                "/api/files/file_download_url",
                "/api/files/batch/file_download_url",
				"/api/video/getplayinfobyvideoid",
				"/api/video/getplayinfobyvideoinfo",
				"/api/video/getplayinfosbyvideoids",
				"/producercontext/product/login"
        );

        http
	        .csrf().disable() // We don't need CSRF for JWT based authentication
	        .exceptionHandling()
	        //   .authenticationEntryPoint(this.authenticationEntryPoint)
	
	        .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authorizeRequests()
	        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers(HttpMethod.GET,"/api/credential/callback/**").permitAll()
	        .antMatchers(permitAllEndpointList.toArray(new String[permitAllEndpointList.size()]))
	        .permitAll()
	        .and()
	        .authorizeRequests()
	        .anyRequest().authenticated() // Protected API End-points
	        .and()
	        .apply(securityConfigurerAdapter())
	        .and()
	        .addFilterBefore(new CORSFilter(), UsernamePasswordAuthenticationFilter.class);
    }
	
	private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }

	
}
