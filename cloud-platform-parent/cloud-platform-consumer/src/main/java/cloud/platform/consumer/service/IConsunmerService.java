package cloud.platform.consumer.service;

import java.util.List;

import cloud.platform.consumer.bean.User;

public interface IConsunmerService {
	String getByIdUseLoadBalancedRestTemplate(int id);
	String noLoadBalancedRestTemplate(int id);
	String ribbonLoadBalancerClient(int id);
	String loadBalancedClient(int id);
	List<User> getUserList();
}
