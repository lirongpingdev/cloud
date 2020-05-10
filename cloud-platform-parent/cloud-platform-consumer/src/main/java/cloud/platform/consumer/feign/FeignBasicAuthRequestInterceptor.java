package cloud.platform.consumer.feign;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignBasicAuthRequestInterceptor  implements RequestInterceptor {
	@Override
	public void apply(RequestTemplate template) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (servletRequestAttributes == null) {
			return;
		}

		HttpServletRequest request = servletRequestAttributes.getRequest();
		//template.header("Authorization", request.getHeader("Authorization"));
		template.header("Authorization", "fdsafdgd");
	}
}
