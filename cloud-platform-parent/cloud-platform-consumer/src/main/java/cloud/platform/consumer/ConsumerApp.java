package cloud.platform.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ConsumerApp {

	/**
	 * 用负载均衡的RestTemplate 注：
	 * 有定义不带负载均衡的restTemplate,因有多个restTemplate,spring分不清是那个，使用时要加@LoadBalanced
	 * 如果只是声明一个restTemplate,使用时可以直接@Autowired，不用加@Qualifier 
	 * 例：多个：
	 * @Autowired
	 * @LoadBalanced 
	 * private RestTemplate useLoadBalancedRestTemplate; 
	 * 单个：
	 * @Autowired 
	 * private RestTemplate useLoadBalancedRestTemplate;
	 */
	@Bean
	@LoadBalanced
	RestTemplate useLoadBalancedRestTemplate() {
		return new RestTemplate();
	}

	/**
	 * 不用负载均衡RestTemplate 注：
	 * 有定义带负载均衡的restTemplate,因有多个restTemplate,spring分不清是那个，使用时要加@Qualifier
	 * 如果只是声明一个restTemplate,使用时可以直接@Autowired，不用加@Qualifier 
	 * 例：多个：
	 * @Autowired
	 * @Qualifier 
	 * private RestTemplate noLoadBalancedRestTemplate; 
	 * 
	 * 单个：
	 * @Autowired 
	 * private RestTemplate noLoadBalancedRestTemplate;
	 */

	@Bean
	@Primary
	RestTemplate noLoadBalancedRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApp.class, args);
		// new
		// SpringApplicationBuilder(ConsumerApp.class).web(WebApplicationType.SERVLET).run(args);
	}

}
