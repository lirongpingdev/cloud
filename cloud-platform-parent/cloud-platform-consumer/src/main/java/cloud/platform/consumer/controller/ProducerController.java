package cloud.platform.consumer.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cloud.platform.consumer.service.IProducerService;

@RestController	
@RequestMapping("/consunmer")
public class ProducerController {

	@Resource
	IProducerService producerService;
	
	@RequestMapping(value = "/getByIdUseFeignClient/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getByIdUseFeignClient(@PathVariable Integer id) {
		return producerService.getByIdUseFeignClient(id);
	}
	
}
