package com.open.cloud.eureka.gateway.util;

import com.open.cloud.eureka.gateway.common.PaginatedResult;
import com.open.cloud.eureka.gateway.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteDefinition;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class FluxPageUtil {
	private static final Logger log;

	public static PaginatedResult getPageResult(final Flux<RouteDefinition> flux, final Integer page, final Integer perPage) {
		final List<RouteDefinition> list = new ArrayList<RouteDefinition>();
		//flux.subscribe((Consumer)list::);
		try {
			return new PaginatedResult().setCount(list.size()).setTotalPage((list.size() % perPage == 0) ? (list.size() / perPage) : (list.size() / perPage + 1)).setCurrentPage(page).setData(list.subList((page - 1) * perPage, (list.size() < perPage) ? list.size() : (page * perPage)));
		} catch (IllegalArgumentException e) {
			FluxPageUtil.log.error("非法页码，请检查页码是否正确");
			throw new CommonException().setCode("1004");
		}
	}

	private FluxPageUtil() {
	}

	static {
		log = LoggerFactory.getLogger((Class) FluxPageUtil.class);
	}
}
