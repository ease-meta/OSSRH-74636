package com.open.cloud.eureka.gateway.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.open.cloud.eureka.gateway.controller.ServiceController;
import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.model.BaseAndTempLimiter;
import com.open.cloud.eureka.gateway.model.GatewayFilterDefinition;
import com.open.cloud.eureka.gateway.model.GatewayPredicateDefinition;
import com.open.cloud.eureka.gateway.model.LimiterDefinition;
import com.open.cloud.eureka.gateway.model.ParamInformation;
import com.open.cloud.eureka.gateway.model.ParamListInformation;
import com.open.cloud.eureka.gateway.model.dto.BaseAndTempLimiterDTO;
import com.open.cloud.eureka.gateway.model.dto.LimiterDefinitionDTO;
import com.open.cloud.eureka.gateway.model.dto.ServiceIDDTO;
import com.open.cloud.eureka.gateway.repository.LimiterConfigRepository;
import com.open.cloud.eureka.gateway.repository.LimiterRepository;
import com.open.cloud.eureka.gateway.route.DynamicRouteServiceImpl;
import com.open.cloud.eureka.gateway.service.impl.RedisRouteServiceImpl;
import com.open.cloud.eureka.gateway.util.LimiterFieldCheckUtil;
import com.open.cloud.eureka.gateway.util.PatternUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;

@Service
public class LimiterService {
	private static final Logger log;
	@Autowired
	private RouteService routeService;
	@Autowired
	private LimiterRepository limiterRepository;
	@Autowired
	private LimiterConfigRepository limiterConfigRepository;
	@Autowired
	private DynamicRouteServiceImpl dynamicRouteService;
	@Autowired
	private ServiceController serviceController;
	@Autowired
	private RedisRouteServiceImpl redisRouteService;
	@Value("${json.resolver.path}")
	String resolverPath;
	@Value("${json.predicate.path}")
	String predicatePath;
	private List<ParamListInformation> resolverList;
	private List<ParamListInformation> predicateList;
	private static final String BASE_PREFIX = "$base";
	private static final String TEMP_PREFIX = "$base$temp";

	public void addBaseServiceLimiterList(final String serviceId, final List<LimiterDefinition> limiters) {
		this.checkService(serviceId);
		this.limiterRepository.addLimiterList(serviceId, "base", limiters);
		this.updateBaseLimiterRoute(serviceId);
	}

	public void addBaseServiceLimiter(final String serviceId, final LimiterDefinition limiter) {
		this.checkService(serviceId);
		this.limiterRepository.addLimiter(serviceId, "base", limiter);
		this.updateBaseLimiterRoute(serviceId);
	}

	public void addBaseInstanceLimiterList(final String serviceId, final String instanceId, final List<LimiterDefinition> limiters) {
		this.checkService(serviceId, instanceId);
		this.limiterRepository.addLimiterList(instanceId, "base", limiters);
		this.updateBaseLimiterRoute(serviceId, instanceId);
	}

	public void addBaseInstanceLimiter(final String serviceId, final String instanceId, final LimiterDefinition limiter) {
		this.checkService(serviceId, instanceId);
		this.limiterRepository.addLimiter(instanceId, "base", limiter);
		this.updateBaseLimiterRoute(serviceId, instanceId);
	}

	public void addTempServiceLimiter(final String serviceId, final List<LimiterDefinition> limiters) {
		this.checkService(serviceId);
		this.limiterRepository.addLimiterList(serviceId, "temp", limiters);
		this.updateTempLimiterRoute(serviceId);
	}

	public void addTempInstanceLimiter(final String serviceId, final String instanceId, final List<LimiterDefinition> limiters) {
		this.checkService(serviceId, instanceId);
		this.limiterRepository.addLimiterList(instanceId, "temp", limiters);
		this.updateTempLimiterRoute(serviceId, instanceId);
	}

	public void deleteServiceLimiter(final String serviceId, final String limiterId) {
		this.checkService(serviceId);
		if (this.checkAndRemoveLimiter(serviceId, "base", limiterId)) {
			this.updateBaseLimiterRoute(serviceId);
		} else {
			if (!this.checkAndRemoveLimiter(serviceId, "temp", limiterId)) {
				throw new CommonException().setCode("1017").setMsg(String.format("Limiter %s does not exist", limiterId));
			}
			this.updateTempLimiterRoute(serviceId);
		}
	}

	public void deleteInstanceLimiter(final String serviceId, final String instanceId, final String limiterId) {
		this.checkService(serviceId, instanceId);
		if (this.checkAndRemoveLimiter(instanceId, "base", limiterId)) {
			this.updateBaseLimiterRoute(serviceId, instanceId);
		} else {
			if (!this.checkAndRemoveLimiter(instanceId, "temp", limiterId)) {
				throw new CommonException().setCode("1017").setMsg(String.format("Limiter %s does not exist", limiterId));
			}
			this.updateTempLimiterRoute(serviceId, instanceId);
		}
	}

	public void updateServiceLimiter(final String serviceId, final LimiterDefinition limiter) {
		this.checkService(serviceId);
		if (this.checkAndUpdateLimiter(serviceId, "base", limiter)) {
			this.updateBaseLimiterRoute(serviceId);
		} else {
			if (!this.checkAndUpdateLimiter(serviceId, "temp", limiter)) {
				throw new CommonException().setCode("1017").setMsg(String.format("Limiter %s does not exist", limiter.getId()));
			}
			this.updateTempLimiterRoute(serviceId);
		}
	}

	public void updateServiceLimiterList(final String serviceId, final List<LimiterDefinition> limiterList) {
		for (final LimiterDefinition limiterDefinition : limiterList) {
			this.updateServiceLimiter(serviceId, limiterDefinition);
		}
	}

	public void updateInstanceLimiter(final String serviceId, final String instanceId, final LimiterDefinition limiter) {
		this.checkService(serviceId, instanceId);
		if (this.checkAndUpdateLimiter(instanceId, "base", limiter)) {
			this.updateBaseLimiterRoute(serviceId, instanceId);
		} else {
			if (!this.checkAndUpdateLimiter(instanceId, "temp", limiter)) {
				throw new CommonException().setCode("1017").setMsg(String.format("Limiter %s does not exist", limiter.getId()));
			}
			this.updateTempLimiterRoute(serviceId, instanceId);
		}
	}

	public void updateInstanceLimiterList(final String serviceId, final String instanceId, final List<LimiterDefinition> limiterList) {
		for (final LimiterDefinition limiterDefinition : limiterList) {
			this.updateInstanceLimiter(serviceId, instanceId, limiterDefinition);
		}
	}

	public void updateLimiterRoute(final String serviceId) {
		this.checkService(serviceId);
		this.updateBaseLimiterRoute(serviceId);
		this.updateTempLimiterRoute(serviceId);
	}

	public void updateLimiterRoute(final String serviceId, final String instanceId) {
		this.checkService(serviceId, instanceId);
		if (!this.limiterRepository.getLimitersByServiceId(instanceId, "base").isEmpty()) {
			this.updateBaseLimiterRoute(serviceId, instanceId);
		}
		if (!this.limiterRepository.getLimitersByServiceId(instanceId, "temp").isEmpty()) {
			this.updateTempLimiterRoute(serviceId, instanceId);
		}
	}

	public void deleteLimiterRoute(final String routeId) {
		final Map<String, RouteDefinition> routeMap = this.routeService.getRoutesMap();
		String url = null;
		if (routeMap.containsKey(routeId + "$base")) {
			final RouteDefinition baseRouteDefinition = routeMap.get(routeId + "$base");
			if (null != baseRouteDefinition) {
				url = baseRouteDefinition.getUri().toString();
			}
			this.dynamicRouteService.delete(routeId + "$base");
			this.limiterConfigRepository.deleteLimiterConfig(routeId + "$base");
		}
		if (routeMap.containsKey(routeId + "$base$temp")) {
			this.dynamicRouteService.delete(routeId + "$base$temp");
			this.limiterConfigRepository.deleteLimiterConfig(routeId + "$base$temp");
		}
		final ServiceIDDTO serviceIDDTO = this.redisRouteService.getServiceName(url);
		if (null != serviceIDDTO) {
			if (!StringUtils.isEmpty((Object) serviceIDDTO.getInstanceID())) {
				final List<RouteDefinition> routeDefinitions = this.redisRouteService.getRoutes(serviceIDDTO.getServiceID(), serviceIDDTO.getInstanceID());
				if (CollectionUtils.isEmpty((Collection) routeDefinitions)) {
					this.limiterRepository.deleteServiceLimiter(serviceIDDTO.getInstanceID());
				}
			} else {
				final List<RouteDefinition> routeDefinitions = this.redisRouteService.getRoutes(serviceIDDTO.getServiceID());
				if (CollectionUtils.isEmpty((Collection) routeDefinitions)) {
					this.limiterRepository.deleteServiceLimiter(serviceIDDTO.getServiceID());
				}
			}
		}
	}

	public BaseAndTempLimiter getLimiter(final String limiterInfoKey) {
		return this.limiterRepository.getBaseAndTempLimiter(limiterInfoKey);
	}

	public BaseAndTempLimiterDTO getLimiters(final String limiterInfoKey) {
		final BaseAndTempLimiterDTO baseAndTempLimiterDTO = new BaseAndTempLimiterDTO();
		final BaseAndTempLimiter limiters = this.getLimiter(limiterInfoKey);
		if (limiters != null) {
			if (!limiters.getBaseLimiter().isEmpty()) {
				final List<LimiterDefinitionDTO> baseLimiter = baseAndTempLimiterDTO.getBaseLimiter();
				for (final LimiterDefinition limiter : limiters.getBaseLimiter()) {
					baseLimiter.add(this.resolverLimiterToDTO(limiter));
				}
			}
			if (!limiters.getTempLimiter().isEmpty()) {
				final List<LimiterDefinitionDTO> tempLimiter = baseAndTempLimiterDTO.getTempLimiter();
				for (final LimiterDefinition limiter : limiters.getTempLimiter()) {
					tempLimiter.add(this.resolverLimiterToDTO(limiter));
				}
			}
		}
		return baseAndTempLimiterDTO;
	}

	private Map<String, RouteDefinition> getBaseRoute(final String serviceId) {
		final List<RouteDefinition> routeList = this.routeService.getRoutes(serviceId);
		final Map<String, RouteDefinition> baseRoute = new HashMap<String, RouteDefinition>();
		for (final RouteDefinition route : routeList) {
			final Matcher matcher = PatternUtil.BASE_PATTERN.matcher(route.getId());
			while (matcher.find()) {
				baseRoute.put(route.getId(), route);
			}
		}
		return baseRoute;
	}

	private Map<String, RouteDefinition> getBaseRoute(final String serviceId, final String instanceId) {
		final List<RouteDefinition> routeList = this.routeService.getRoutes(serviceId, instanceId);
		final Map<String, RouteDefinition> baseRoute = new HashMap<String, RouteDefinition>();
		for (final RouteDefinition route : routeList) {
			final Matcher matcher = PatternUtil.BASE_PATTERN.matcher(route.getId());
			while (matcher.find()) {
				baseRoute.put(route.getId(), route);
			}
		}
		return baseRoute;
	}

	private Map<String, RouteDefinition> getBaseLimiterRoute(final String serviceId) {
		final List<RouteDefinition> routeList = this.routeService.getRoutes(serviceId);
		final Map<String, RouteDefinition> baseLimiterRoute = new HashMap<String, RouteDefinition>();
		for (final RouteDefinition route : routeList) {
			final Matcher matcher = PatternUtil.BASE_LIMITER_PATTERN.matcher(route.getId());
			while (matcher.find()) {
				baseLimiterRoute.put(route.getId(), route);
			}
		}
		return baseLimiterRoute;
	}

	private Map<String, RouteDefinition> getBaseLimiterRoute(final String serviceId, final String instanceId) {
		final List<RouteDefinition> routeList = this.routeService.getRoutes(serviceId, instanceId);
		final Map<String, RouteDefinition> baseLimiterRoute = new HashMap<String, RouteDefinition>();
		for (final RouteDefinition route : routeList) {
			final Matcher matcher = PatternUtil.BASE_LIMITER_PATTERN.matcher(route.getId());
			while (matcher.find()) {
				baseLimiterRoute.put(route.getId(), route);
			}
		}
		return baseLimiterRoute;
	}

	private Map<String, RouteDefinition> getTempLimiterRoute(final String serviceId) {
		final List<RouteDefinition> routeList = this.routeService.getRoutes(serviceId);
		final Map<String, RouteDefinition> tempLimiterRoute = new HashMap<String, RouteDefinition>();
		for (final RouteDefinition route : routeList) {
			final Matcher matcher = PatternUtil.TEMP_LIMITER_PATTERN.matcher(route.getId());
			while (matcher.find()) {
				tempLimiterRoute.put(route.getId(), route);
			}
		}
		return tempLimiterRoute;
	}

	private Map<String, RouteDefinition> getTempLimiterRoute(final String serviceId, final String instanceId) {
		final List<RouteDefinition> routeList = this.routeService.getRoutes(serviceId, instanceId);
		final Map<String, RouteDefinition> tempLimiterRoute = new HashMap<String, RouteDefinition>();
		for (final RouteDefinition route : routeList) {
			final Matcher matcher = PatternUtil.TEMP_LIMITER_PATTERN.matcher(route.getId());
			while (matcher.find()) {
				tempLimiterRoute.put(route.getId(), route);
			}
		}
		return tempLimiterRoute;
	}

	private void saveRoute(final RouteDefinition route, final List<LimiterDefinition> limiters) {
		final List<FilterDefinition> filters = (List<FilterDefinition>) route.getFilters();
		final List<PredicateDefinition> predicates = (List<PredicateDefinition>) route.getPredicates();
		final List<String> keyResolver = new ArrayList<String>();
		for (final LimiterDefinition limiter : limiters) {
			if (!keyResolver.contains(limiter.getFilter().getArgs().get("key-resolver"))) {
				keyResolver.add(limiter.getFilter().getArgs().get("key-resolver"));
				if (limiter.getFilter() != null) {
					final FilterDefinition baseFilter = new FilterDefinition();
					baseFilter.setName(limiter.getFilter().getName());
					baseFilter.setArgs((Map) limiter.getFilter().getArgs());
					filters.add(baseFilter);
				}
				if (limiter.getPredicate() == null) {
					continue;
				}
				final PredicateDefinition predicate = new PredicateDefinition();
				predicate.setName(limiter.getPredicate().getName());
				predicate.setArgs((Map) limiter.getPredicate().getArgs());
				predicates.add(predicate);
			}
		}
		this.dynamicRouteService.add(route);
		this.routeService.saveFilterDefinition(route);
	}

	private void checkService(final String serviceId) {
		final Map<String, RouteDefinition> baseRoutes = this.getBaseRoute(serviceId);
		if (baseRoutes == null || baseRoutes.isEmpty()) {
			throw new CommonException().setCode("1017").setMsg(String.format("service %s does not exist", serviceId));
		}
	}

	private void checkService(final String serviceId, final String instanceId) {
		final Map<String, RouteDefinition> baseRoutes = this.getBaseRoute(serviceId, instanceId);
		if (baseRoutes == null || baseRoutes.isEmpty()) {
			throw new CommonException().setCode("1017").setMsg(String.format("service %s or instance %s does not exist", serviceId, instanceId));
		}
	}

	private boolean checkAndRemoveLimiter(final String limiterInfoKey, final String type, final String limiterId) {
		final List<LimiterDefinition> limiters = this.limiterRepository.getLimitersByServiceId(limiterInfoKey, type);
		for (final LimiterDefinition limiter : limiters) {
			if (limiter.getId().equals(limiterId)) {
				this.limiterRepository.deleteLimiter(limiterInfoKey, type, limiterId);
				return true;
			}
		}
		return false;
	}

	private boolean checkAndUpdateLimiter(final String limiterInfoKey, final String type, final LimiterDefinition newLimiter) {
		final List<LimiterDefinition> limiters = this.limiterRepository.getLimitersByServiceId(limiterInfoKey, type);
		for (final LimiterDefinition limiter : limiters) {
			if (limiter.getId().equals(newLimiter.getId())) {
				this.limiterRepository.updateLimiter(limiterInfoKey, type, newLimiter);
				return true;
			}
		}
		return false;
	}

	private void updateBaseLimiterRoute(final String serviceId) {
		final Map<String, RouteDefinition> baseRoutes = this.getBaseRoute(serviceId);
		final Map<String, RouteDefinition> tempLimiterRoutes = this.getTempLimiterRoute(serviceId);
		this.updateBaseLimiterRoute(baseRoutes, tempLimiterRoutes, serviceId);
	}

	private void updateBaseLimiterRoute(final String serviceId, final String instanceId) {
		final Map<String, RouteDefinition> baseRoutes = this.getBaseRoute(serviceId, instanceId);
		final Map<String, RouteDefinition> tempLimiterRoutes = this.getTempLimiterRoute(serviceId, instanceId);
		this.updateBaseLimiterRoute(baseRoutes, tempLimiterRoutes, instanceId);
	}

	private void updateTempLimiterRoute(final String serviceId) {
		final Map<String, RouteDefinition> baseRoutes = this.getBaseRoute(serviceId);
		this.updateTempLimiterRoute(baseRoutes, serviceId);
	}

	private void updateTempLimiterRoute(final String serviceId, final String instanceId) {
		final Map<String, RouteDefinition> baseRoutes = this.getBaseRoute(serviceId, instanceId);
		this.updateTempLimiterRoute(baseRoutes, instanceId);
	}

	private void updateBaseLimiterRoute(final Map<String, RouteDefinition> baseRoutes, final Map<String, RouteDefinition> tempLimiterRoutes, final String limiterInfoKey) {
		for (final RouteDefinition route : baseRoutes.values()) {
			final RouteDefinition baseRoute = (RouteDefinition) JSON.parseObject(JSON.toJSONString((Object) route), (Class) RouteDefinition.class);
			baseRoute.setId(baseRoute.getId() + "$base");
			baseRoute.setOrder(baseRoute.getOrder() - 1);
			final List<LimiterDefinition> baseLimiters = this.limiterRepository.getLimitersByServiceId(limiterInfoKey, "base");
			final List<LimiterDefinition> tempLimiters = this.limiterRepository.getLimitersByServiceId(limiterInfoKey, "temp");
			this.saveRoute(baseRoute, baseLimiters);
			this.limiterConfigRepository.updateLimiterConfig(baseRoute.getId(), baseLimiters);
			if (tempLimiterRoutes.containsKey(route.getId() + "$base$temp")) {
				final RouteDefinition tempRoute = (RouteDefinition) JSON.parseObject(JSON.toJSONString((Object) route), (Class) RouteDefinition.class);
				tempRoute.setId(tempRoute.getId() + "$base$temp");
				tempRoute.setOrder(tempRoute.getOrder() - 2);
				final List<LimiterDefinition> limiters = new ArrayList<LimiterDefinition>(tempLimiters);
				limiters.addAll(baseLimiters);
				this.limiterConfigRepository.updateLimiterConfig(tempRoute.getId(), limiters);
				this.saveRoute(tempRoute, limiters);
			}
		}
	}

	private void updateTempLimiterRoute(final Map<String, RouteDefinition> baseRoutes, final String limiterInfoKey) {
		final List<LimiterDefinition> baseLimiters = this.limiterRepository.getLimitersByServiceId(limiterInfoKey, "base");
		final List<LimiterDefinition> tempLimiters = this.limiterRepository.getLimitersByServiceId(limiterInfoKey, "temp");
		final List<LimiterDefinition> limiters = new ArrayList<LimiterDefinition>(tempLimiters);
		limiters.addAll(baseLimiters);
		for (final RouteDefinition route : baseRoutes.values()) {
			final RouteDefinition tempRoute = (RouteDefinition) JSON.parseObject(JSON.toJSONString((Object) route), (Class) RouteDefinition.class);
			tempRoute.setId(tempRoute.getId() + "$base$temp");
			tempRoute.setOrder(tempRoute.getOrder() - 2);
			this.limiterConfigRepository.updateLimiterConfig(tempRoute.getId(), limiters);
			this.saveRoute(tempRoute, limiters);
		}
	}

	public LimiterDefinitionDTO resolverLimiterToDTO(final LimiterDefinition limiterDefinition) {
		try {
			final LimiterDefinitionDTO limiterDefinitionDTO = new LimiterDefinitionDTO();
			limiterDefinitionDTO.setId(limiterDefinition.getId());
			final Map<String, String> limiterFilterArgs = limiterDefinition.getFilter().getArgs();
			final List<ParamListInformation> reList = Optional.ofNullable(this.resolverList).orElse(JSON.parseArray(FileUtils.readFileToString(new File(this.resolverPath), "UTF-8"), (Class) ParamListInformation.class));
			for (final ParamListInformation param : reList) {
				if (param.getName().equals(limiterFilterArgs.get("key-resolver"))) {
					final List<ParamInformation> args = param.getArgs();
					for (final ParamInformation temp : args) {
						if (limiterFilterArgs.containsKey(temp.getName())) {
							temp.setValue(limiterFilterArgs.get(temp.getName()));
						}
					}
					limiterDefinitionDTO.setFilter(param);
				}
			}
			if (limiterDefinition.getPredicate() != null) {
				final Map<String, String> limiterPredicateArgs = limiterDefinition.getPredicate().getArgs();
				final List<ParamListInformation> paList = Optional.ofNullable(this.predicateList).orElse(JSON.parseArray(FileUtils.readFileToString(new File(this.predicatePath), "UTF-8"), (Class) ParamListInformation.class));
				for (final ParamListInformation param2 : paList) {
					if (param2.getName().equals(limiterDefinition.getPredicate().getName())) {
						final List<ParamInformation> args2 = param2.getArgs();
						for (final ParamInformation temp2 : args2) {
							if (limiterPredicateArgs.containsKey(temp2.getName())) {
								temp2.setValue(limiterPredicateArgs.get(temp2.getName()));
							}
						}
						limiterDefinitionDTO.setPredicate(param2);
					}
				}
			}
			return limiterDefinitionDTO;
		} catch (IOException e) {
			LimiterService.log.error("File loading error! Path: %s,{}", (Object) this.resolverPath, (Object) e);
			throw new CommonException().setCode("1006");
		}
	}

	public LimiterDefinition resolverDTOtoLimiter(final LimiterDefinitionDTO limiterDefinitionDTO) {
		final LimiterDefinition limiterDefinition = new LimiterDefinition();
		limiterDefinition.setId(limiterDefinitionDTO.getId());
		if (limiterDefinitionDTO.getFilter() != null) {
			final GatewayFilterDefinition filter = new GatewayFilterDefinition();
			filter.setName("RequestRateLimiter");
			final Map<String, String> args = filter.getArgs();
			args.put("key-resolver", limiterDefinitionDTO.getFilter().getName());
			args.put("rate-limiter", "#{@customRedisRateLimiter}");
			LimiterFieldCheckUtil.fieldCheck(limiterDefinitionDTO.getFilter().getName(), limiterDefinitionDTO.getFilter().getArgs());
			for (final ParamInformation paramInformation : limiterDefinitionDTO.getFilter().getArgs()) {
				args.put(paramInformation.getName(), paramInformation.getValue());
			}
			limiterDefinition.setFilter(filter);
		}
		if (limiterDefinitionDTO.getPredicate() != null && limiterDefinitionDTO.getPredicate().getName() != null) {
			final GatewayPredicateDefinition predicate = new GatewayPredicateDefinition();
			predicate.setName(limiterDefinitionDTO.getPredicate().getName());
			final Map<String, String> args = predicate.getArgs();
			for (final ParamInformation paramInformation : limiterDefinitionDTO.getPredicate().getArgs()) {
				args.put(paramInformation.getName(), paramInformation.getValue());
			}
			limiterDefinition.setPredicate(predicate);
		}
		return limiterDefinition;
	}

	public void addAndUpdateServiceLimiter(final String serviceId, final String type, final List<LimiterDefinitionDTO> limiterDefinitionDTOs) {
		if (type != null && type.equals("base")) {
			this.addAndUpdateLimiter(serviceId, "base", limiterDefinitionDTOs);
			this.updateBaseLimiterRoute(serviceId);
		}
		if (type != null && type.equals("temp")) {
			this.addAndUpdateLimiter(serviceId, "temp", limiterDefinitionDTOs);
			this.updateTempLimiterRoute(serviceId);
		}
	}

	public void addAndUpdateInstanceLimiter(final String serviceId, final String instanceId, final String type, final List<LimiterDefinitionDTO> limiterDefinitionDTOs) {
		if (type != null && type.equals("base")) {
			this.addAndUpdateLimiter(instanceId, "base", limiterDefinitionDTOs);
			this.updateBaseLimiterRoute(serviceId, instanceId);
		}
		if (type != null && type.equals("temp")) {
			this.addAndUpdateLimiter(instanceId, "temp", limiterDefinitionDTOs);
			this.updateTempLimiterRoute(serviceId, instanceId);
		}
	}

	private void addAndUpdateLimiter(final String serviceId, final String type, final List<LimiterDefinitionDTO> limiters) {
		for (final LimiterDefinitionDTO LimiterDefinitionDTO : limiters) {
			final LimiterDefinition limiterDefinition = this.resolverDTOtoLimiter(LimiterDefinitionDTO);
			if (Strings.isNullOrEmpty(limiterDefinition.getId())) {
				this.limiterRepository.addLimiter(serviceId, type, limiterDefinition);
			} else {
				this.limiterRepository.updateLimiter(serviceId, type, limiterDefinition);
			}
		}
	}

	static {
		log = LoggerFactory.getLogger((Class) LimiterService.class);
	}
}
