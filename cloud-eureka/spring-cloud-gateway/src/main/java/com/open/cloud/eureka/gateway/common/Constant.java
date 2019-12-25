package com.open.cloud.eureka.gateway.common;

public class Constant
{
    public static final String SUCCESS_CODE = "0000";
    public static final String SUCCESS_MSG = "Success";
    public static final String COMMON_ERROR = "1000";
    public static final String ADD_ROUTE_ERROR = "1001";
    public static final String UPDATE_ROUTE_ERROR = "1002";
    public static final String DELETE_ROUTE_ERROR = "1003";
    public static final String PAGE_PARM_ERROR = "1004";
    public static final String REQUEST_PARAM_ERROR = "1005";
    public static final String JSON_READ_ERROR = "1006";
    public static final String ROUTE_SAME_NAME = "1007";
    public static final String ROUTE_NOT_FOUND = "1008";
    public static final String SERVICE_NOT_FOUND = "1009";
    public static final String INSTANCE_NOT_FOUND = "1010";
    public static final String WITHOUT_PREDICATE = "1011";
    public static final String FOUND_NO_SERVER = "1012";
    public static final String NO_AUTH = "1013";
    public static final String FIELD_ILLEGAL = "1014";
    public static final String RESOLVER_NOT_FOUND = "1015";
    public static final String PARAM_RESOLVER_ERROR = "1016";
    public static final String RESOURCE_NOT_FOUND = "1017";
    public static final String HYSTRIX_KEY_EXIST = "1018";
    public static final String RATE_CONFIG = "rate_config";
    public static final String REDIS_RATE_LIMITER = "RequestRateLimiter";
    public static final String KEY_RESOLVER = "key-resolver";
    public static final String LIMITER_KEY = "custom-redis-rate-limiter.key";
    public static final String LIMITER_VALUE = "custom-redis-rate-limiter.value";
    public static final String LIMITER_SCOPE = "custom-redis-rate-limiter.scope";
    public static final String BURST_CAPACITY = "custom-redis-rate-limiter.burstCapacity";
    public static final String REPLENISH_RATE = "custom-redis-rate-limiter.replenishRate";
    public static final String BLANK = "";
    public static final String BASE_LIMITER = "base";
    public static final String TEMP_LIMITER = "temp";
    public static final String RIBBON_KEY = "ribbon";
    public static final String HYSTRIX_KEY = "hystrix";
    public static final String RIBBON_SERVICE_KEY = "ribbon_service";
    public static final String GATEWAY_KEY = "gateway_routes";
    public static final String PER_PAGE = "10";
    public static final String PAGE = "1";
    public static final Integer DEFAULT_HYSTRIX_TIMEOUT;
    public static final Integer DEFAULT_HYSTRIX_MAX_REQUEST;
    public static final String DATE_PREDICATE_BEFORE = "Before";
    public static final String DATE_PREDICATE_AFTER = "After";
    public static final String DATE_PREDICATE_BETWEEN = "Between";
    public static final String DATE_TIME_BEFORE_AFTER = "datetime";
    public static final String DATE_TIME_BETWEEN_AFTER = "datetime1";
    public static final String DATE_TIME_BETWEEN_BEFORE = "datetime2";
    
    private Constant() {
    }
    
    static {
        DEFAULT_HYSTRIX_TIMEOUT = 3000;
        DEFAULT_HYSTRIX_MAX_REQUEST = 100;
    }
}
