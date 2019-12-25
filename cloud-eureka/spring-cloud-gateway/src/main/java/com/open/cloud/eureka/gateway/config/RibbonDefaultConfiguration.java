package com.open.cloud.eureka.gateway.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.IRule;
import com.open.cloud.eureka.gateway.model.RibbonDefinition;
import com.open.cloud.eureka.gateway.service.LoadBalancerService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.PropertiesFactory;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClients(defaultConfiguration = {RibbonDefaultConfiguration.class})
public class RibbonDefaultConfiguration {
	private static final Logger log = LoggerFactory.getLogger(RibbonDefaultConfiguration.class);

	@Value("${ribbon.client.name:#{null}}")
	private String name;

	@Value("${ribbon.NFLoadBalancerRuleClassName:com.netflix.loadbalancer.RoundRobinRule}")
	private String className;

	@Autowired(required = false)
	private IClientConfig config;


	@Autowired
	private PropertiesFactory propertiesFactory;


	@Autowired
	private LoadBalancerService loadBalancerService;


	@Bean
	public IRule ribbonRule() {
		if (StringUtils.isEmpty(this.name)) {
			return null;
		}

		RibbonDefinition ribbonDefinition = (RibbonDefinition) this.loadBalancerService.getLoadBalancerByName(this.name);
		if (ribbonDefinition != null) {
			AbstractLoadBalancerRule rule = null;
			try {
				rule = (AbstractLoadBalancerRule) Class.forName(ribbonDefinition.getRibbonClass()).newInstance();
				rule.initWithNiwsConfig(this.config);
				return (IRule) rule;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				log.error("Invalid ribbon rule:{}", e);
			}
		}

		if (this.propertiesFactory.isSet(IRule.class, this.name)) {
			return (IRule) this.propertiesFactory.get(IRule.class, this.config, this.name);
		}
		try {
			AbstractLoadBalancerRule rule = (AbstractLoadBalancerRule) Class.forName(this.className).newInstance();
			rule.initWithNiwsConfig(this.config);
			return (IRule) rule;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			log.error("Invalid ribbon rule:{}", e);

			return null;
		}
	}
}
