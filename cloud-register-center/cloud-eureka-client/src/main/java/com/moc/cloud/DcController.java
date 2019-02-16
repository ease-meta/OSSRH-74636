package com.moc.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DcController {

	@Autowired
	LoadBalancerClient loadBalancerClient;
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	DiscoveryClient discoveryClient;

	@GetMapping("/dc")
	public String dc() {
		String services = "Services: " + discoveryClient.getServices();
		System.out.println(services);
		return services;
	}

	@GetMapping("/consumer")
	public String consumer() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("eurekaclient");
		String url = "http://" + "eurekaclient" + ":" + serviceInstance.getPort() + "/dc";
		System.out.println(url);
		return restTemplate.getForObject(url, String.class);
	}
}