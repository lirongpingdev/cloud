package cloud.platform.consumer.feign;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.codec.Decoder;
import feign.codec.Encoder;

@Configuration
public class FeignConfiguration {
	@Bean
	public Decoder feignDecoder() {
		@SuppressWarnings("rawtypes")
		HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(objectMapper());
		ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
		return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
	}

	@Bean
	public Encoder feignEncoder() {
		@SuppressWarnings("rawtypes")
		HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(objectMapper());
		ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
		return new SpringEncoder(objectFactory);
	}

	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		return objectMapper;
	}

	/**
	 * 
	 * application.yml配
	    logging:
		  level:
		    cloud.platform.consumer.service.impl: DEBUG
		    com.worldunion.clientServer.controller: DEBUG
		    root: info
		    com.memorynotfound: DEBUG
		    org.springframework.web: DEBUG
		    org.springframework.security: INFO
	 */
	@Bean
	public Logger.Level feignLoggerLevel() {
		return feign.Logger.Level.FULL;
	}

	@Bean
	public static Request.Options requestOptions(ConfigurableEnvironment env) {
		int ribbonReadTimeout = 50000;
		int ribbonConnectionTimeout = 40000;

		return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
	}

	/**
	 * 如果不自定义Feign.Builder，会优先配置 feign.hystrix.HystrixFeign.Builder extends
	 * Feign.Builder，该类会让 Feign 的内部调用受到 Hystrix 的控制
	 * 
	 * @return
	 */
	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}

	/**
	 * 创建Feign请求拦截器，在发送请求前设置认证的token,各个微服务将token设置到环境变量中来达到通用
	 * 
	 * @return
	 */
	@Bean
	public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new FeignBasicAuthRequestInterceptor();
	}
}
