package io.github.meta.ease.gateway.controller;

import io.github.meta.ease.gateway.core.GatewayConstant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/11/7 18:51
 */
@RestController
@RequestMapping(value = GatewayConstant.GATWAYAPI_PATH_PREFIX + "/cache", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class CacheController {
}
