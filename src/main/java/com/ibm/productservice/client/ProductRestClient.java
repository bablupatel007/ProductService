package com.ibm.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("Tax-Services")
public interface ProductRestClient {

	@GetMapping("/tax/{name}")
	public Long getTax(@PathVariable(value = "name") String name);
}
