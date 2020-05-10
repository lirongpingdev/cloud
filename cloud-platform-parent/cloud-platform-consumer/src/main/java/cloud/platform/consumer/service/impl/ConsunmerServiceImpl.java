package cloud.platform.consumer.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cloud.platform.consumer.bean.User;
import cloud.platform.consumer.service.IConsunmerService;

@Service
public class ConsunmerServiceImpl implements IConsunmerService{

	/**
	 * ConsumerApp.class义过用负载均衡
	 */
	@Autowired
	@LoadBalanced
	private RestTemplate useLoadBalancedRestTemplate;
	
	/**
	 * ConsumerApp.class义过不用负载均衡
	 */
	@Qualifier
	@Autowired
	private RestTemplate noLoadBalancedRestTemplate;
	
	//以下注入是负载均衡客户端：LoadBalancedClient是接口,下面只有一个RibbonLoadBalancerClient实现类
	/**
	 * 是一个接口，不一定要用ribbon做负载均衡时可用
	 */
	@Autowired
	private LoadBalancerClient loadBalancedClient;
	
	/**
	 * 实现类：一定是用ribbon做负载均衡
	 */
	@Autowired
	private RibbonLoadBalancerClient ribbonLoadBalancerClient;
	
	@Override
	public String getByIdUseLoadBalancedRestTemplate(int id) {
		//用负载均衡一定要用instance实例，url不可以用具体的IP:port
		/**实例为application.yml定义：
		 * eureka:
		 *	  instance:
		 *      appname: cloud-platform-producer
		 */
		return useLoadBalancedRestTemplate.getForObject("http://cloud-platform-producer/producercontext/product/getByIdUseLoadBalancedRestTemplate/"+id, String.class);
	}

	@Override
	public String noLoadBalancedRestTemplate(int id) {
		//不用负载均衡调用url可以用具体的IP:port,但如果用负载均衡一定要用instance实例
		/**实例为application.yml定义：
		 * eureka:
		 *	  instance:
		 *      appname: cloud-platform-producer
		 */
		return noLoadBalancedRestTemplate.getForObject("http://cloud-platform-producer/producercontext/product/noLoadBalancedRestTemplate/"+id, String.class);
	}
	
	@Override
	public String ribbonLoadBalancerClient(int id) {
		ServiceInstance instance = ribbonLoadBalancerClient.choose("cloud-platform-producer");
		System.out.println("Host=" + instance.getHost() + ",port=" + instance.getPort() + ",instanceId="
				+ instance.getInstanceId() + ",uri=" + instance.getUri());

		/* ribbonLoadBalancerClient.execute(serviceId, request) */
		/*
		 * return ribbonLoadBalancerClient.getForObject(
		 * "http://cloud-platform-producer/producerapp/product/ribbonLoadBalancerClient/"
		 * + id, String.class);
		 */
		
		return null;
	}

	@Override
	public String loadBalancedClient(int id) {
		ServiceInstance instance = loadBalancedClient.choose("cloud-platform-producer");
		System.out.println("Host=" + instance.getHost() + ",port=" + instance.getPort() + ",instanceId="
				+ instance.getInstanceId() + ",uri=" + instance.getUri());
		/*
		 * loadBalancedClient.reconstructURI(instance, original); return
		 * loadBalancedClient.getForObject(
		 * "http://cloud-platform-producer/producerapp/product/loadBalancedClient/" +
		 * id, String.class);
		 */
		return null;
	}

	@Override
	public List<User> getUserList() {
		return useLoadBalancedRestTemplate.getForObject("http://cloud-platform-producer/producercontext/product/getUserList/", List.class);
	}

}



















