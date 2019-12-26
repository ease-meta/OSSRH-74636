package com.open.cloud.eureka.gateway.feign;

import org.springframework.cloud.openfeign.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@FeignClient("auth-service")
public interface AuthFeignClient
{
    @GetMapping({ "/user/get" })
    ResponseEntity<?> getUser(@RequestHeader("Authorization") final String token);
    
    @GetMapping({ "/user/auth" })
    Object getAuth(@RequestHeader("Authorization") final String token);
}
