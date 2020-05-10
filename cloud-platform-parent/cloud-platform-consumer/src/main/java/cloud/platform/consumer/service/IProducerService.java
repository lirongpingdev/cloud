package cloud.platform.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cloud-platform-producer")
public interface IProducerService {
	
	@RequestMapping(value = "/producercontext/product/getByIdUseFeignClient/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getByIdUseFeignClient(@PathVariable Integer id);
	
}