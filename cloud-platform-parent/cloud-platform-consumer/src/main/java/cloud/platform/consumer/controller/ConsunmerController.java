package cloud.platform.consumer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cloud.platform.consumer.bean.User;
import cloud.platform.consumer.service.IConsunmerService;

@RefreshScope
@RestController	
@RequestMapping("/consunmer")
public class ConsunmerController {
	
	@Value("${jdbc.url}")
	private String jdbcurl;
	
	@Value("${application.name}")
	private String applicationName;
	
	@Autowired
	IConsunmerService consunmerService;
	
	@RequestMapping(value = "/applicationName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String applicationName() {
		return applicationName;
	}
	
	@RequestMapping(value = "/jdbcurl", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String jdbcurl() {
		return jdbcurl;
	}
	
	@RequestMapping(value = "/getByIdUseLoadBalancedRestTemplate/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getByIdUseLoadBalancedRestTemplate(@PathVariable Integer id) {
		return consunmerService.getByIdUseLoadBalancedRestTemplate(id);
	}
	
	@RequestMapping(value = "/noLoadBalancedRestTemplate/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String noLoadBalancedRestTemplate(@PathVariable Integer id) {
		return consunmerService.noLoadBalancedRestTemplate(id);
	}
	
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUserList() {
		return consunmerService.getUserList();
	}
	
	@GetMapping("/getPort")
	public Map<String,String> getPort(HttpServletRequest request) {
		String localAddr = request.getLocalAddr();
		int serverPort = request.getServerPort();
		String contextPath = request.getContextPath();
		Map<String,String> map = new HashMap<String,String>();
		map.put("localAddr", localAddr);
		map.put("serverPort", serverPort+"");
		map.put("contextPath", contextPath);
		System.out.println("-------------------map="+map);
		return map;
	}
	
}
