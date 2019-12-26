package com.open.cloud.eureka.gateway.model;

import com.fasterxml.jackson.annotation.*;
import java.util.*;

public class BasicRouteDefinition
{
    private String id;
    private String host;
    private String path;
    private Map<String, String> header;
    private Map<String, String> cookie;
    private String method;
    @JsonProperty("hystrix_flag")
    private Boolean hystrixFlag;
    @JsonProperty("fallback_uri")
    private String fallbackUri;
    @JsonProperty("hystrix_name")
    private String hystrixName;
    @JsonProperty("strip_prefix")
    private Boolean stripPrefix;
    private String uri;
    private Integer order;
    
    public String getId() {
        return this.id;
    }
    
    public String getHost() {
        return this.host;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public Map<String, String> getHeader() {
        return this.header;
    }
    
    public Map<String, String> getCookie() {
        return this.cookie;
    }
    
    public String getMethod() {
        return this.method;
    }
    
    public Boolean getHystrixFlag() {
        return this.hystrixFlag;
    }
    
    public String getFallbackUri() {
        return this.fallbackUri;
    }
    
    public String getHystrixName() {
        return this.hystrixName;
    }
    
    public Boolean getStripPrefix() {
        return this.stripPrefix;
    }
    
    public String getUri() {
        return this.uri;
    }
    
    public Integer getOrder() {
        return this.order;
    }
    
    public BasicRouteDefinition setId(final String id) {
        this.id = id;
        return this;
    }
    
    public BasicRouteDefinition setHost(final String host) {
        this.host = host;
        return this;
    }
    
    public BasicRouteDefinition setPath(final String path) {
        this.path = path;
        return this;
    }
    
    public BasicRouteDefinition setHeader(final Map<String, String> header) {
        this.header = header;
        return this;
    }
    
    public BasicRouteDefinition setCookie(final Map<String, String> cookie) {
        this.cookie = cookie;
        return this;
    }
    
    public BasicRouteDefinition setMethod(final String method) {
        this.method = method;
        return this;
    }
    
    public BasicRouteDefinition setHystrixFlag(final Boolean hystrixFlag) {
        this.hystrixFlag = hystrixFlag;
        return this;
    }
    
    public BasicRouteDefinition setFallbackUri(final String fallbackUri) {
        this.fallbackUri = fallbackUri;
        return this;
    }
    
    public BasicRouteDefinition setHystrixName(final String hystrixName) {
        this.hystrixName = hystrixName;
        return this;
    }
    
    public BasicRouteDefinition setStripPrefix(final Boolean stripPrefix) {
        this.stripPrefix = stripPrefix;
        return this;
    }
    
    public BasicRouteDefinition setUri(final String uri) {
        this.uri = uri;
        return this;
    }
    
    public BasicRouteDefinition setOrder(final Integer order) {
        this.order = order;
        return this;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BasicRouteDefinition)) {
            return false;
        }
        final BasicRouteDefinition other = (BasicRouteDefinition)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        Label_0065: {
            if (this$id == null) {
                if (other$id == null) {
                    break Label_0065;
                }
            }
            else if (this$id.equals(other$id)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$host = this.getHost();
        final Object other$host = other.getHost();
        Label_0102: {
            if (this$host == null) {
                if (other$host == null) {
                    break Label_0102;
                }
            }
            else if (this$host.equals(other$host)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$path = this.getPath();
        final Object other$path = other.getPath();
        Label_0139: {
            if (this$path == null) {
                if (other$path == null) {
                    break Label_0139;
                }
            }
            else if (this$path.equals(other$path)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$header = this.getHeader();
        final Object other$header = other.getHeader();
        Label_0176: {
            if (this$header == null) {
                if (other$header == null) {
                    break Label_0176;
                }
            }
            else if (this$header.equals(other$header)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$cookie = this.getCookie();
        final Object other$cookie = other.getCookie();
        Label_0213: {
            if (this$cookie == null) {
                if (other$cookie == null) {
                    break Label_0213;
                }
            }
            else if (this$cookie.equals(other$cookie)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$method = this.getMethod();
        final Object other$method = other.getMethod();
        Label_0250: {
            if (this$method == null) {
                if (other$method == null) {
                    break Label_0250;
                }
            }
            else if (this$method.equals(other$method)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$hystrixFlag = this.getHystrixFlag();
        final Object other$hystrixFlag = other.getHystrixFlag();
        Label_0287: {
            if (this$hystrixFlag == null) {
                if (other$hystrixFlag == null) {
                    break Label_0287;
                }
            }
            else if (this$hystrixFlag.equals(other$hystrixFlag)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$fallbackUri = this.getFallbackUri();
        final Object other$fallbackUri = other.getFallbackUri();
        Label_0324: {
            if (this$fallbackUri == null) {
                if (other$fallbackUri == null) {
                    break Label_0324;
                }
            }
            else if (this$fallbackUri.equals(other$fallbackUri)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$hystrixName = this.getHystrixName();
        final Object other$hystrixName = other.getHystrixName();
        Label_0361: {
            if (this$hystrixName == null) {
                if (other$hystrixName == null) {
                    break Label_0361;
                }
            }
            else if (this$hystrixName.equals(other$hystrixName)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$stripPrefix = this.getStripPrefix();
        final Object other$stripPrefix = other.getStripPrefix();
        Label_0398: {
            if (this$stripPrefix == null) {
                if (other$stripPrefix == null) {
                    break Label_0398;
                }
            }
            else if (this$stripPrefix.equals(other$stripPrefix)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$uri = this.getUri();
        final Object other$uri = other.getUri();
        Label_0435: {
            if (this$uri == null) {
                if (other$uri == null) {
                    break Label_0435;
                }
            }
            else if (this$uri.equals(other$uri)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$order = this.getOrder();
        final Object other$order = other.getOrder();
        if (this$order == null) {
            if (other$order == null) {
                return true;
            }
        }
        else if (this$order.equals(other$order)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof BasicRouteDefinition;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $host = this.getHost();
        result = result * 59 + (($host == null) ? 43 : $host.hashCode());
        final Object $path = this.getPath();
        result = result * 59 + (($path == null) ? 43 : $path.hashCode());
        final Object $header = this.getHeader();
        result = result * 59 + (($header == null) ? 43 : $header.hashCode());
        final Object $cookie = this.getCookie();
        result = result * 59 + (($cookie == null) ? 43 : $cookie.hashCode());
        final Object $method = this.getMethod();
        result = result * 59 + (($method == null) ? 43 : $method.hashCode());
        final Object $hystrixFlag = this.getHystrixFlag();
        result = result * 59 + (($hystrixFlag == null) ? 43 : $hystrixFlag.hashCode());
        final Object $fallbackUri = this.getFallbackUri();
        result = result * 59 + (($fallbackUri == null) ? 43 : $fallbackUri.hashCode());
        final Object $hystrixName = this.getHystrixName();
        result = result * 59 + (($hystrixName == null) ? 43 : $hystrixName.hashCode());
        final Object $stripPrefix = this.getStripPrefix();
        result = result * 59 + (($stripPrefix == null) ? 43 : $stripPrefix.hashCode());
        final Object $uri = this.getUri();
        result = result * 59 + (($uri == null) ? 43 : $uri.hashCode());
        final Object $order = this.getOrder();
        result = result * 59 + (($order == null) ? 43 : $order.hashCode());
        return result;
    }
    
    public BasicRouteDefinition() {
        this.header = new LinkedHashMap<String, String>();
        this.cookie = new LinkedHashMap<String, String>();
    }
    
    @Override
    public String toString() {
        return "BasicRouteDefinition(id=" + this.getId() + ", host=" + this.getHost() + ", path=" + this.getPath() + ", header=" + this.getHeader() + ", cookie=" + this.getCookie() + ", method=" + this.getMethod() + ", hystrixFlag=" + this.getHystrixFlag() + ", fallbackUri=" + this.getFallbackUri() + ", hystrixName=" + this.getHystrixName() + ", stripPrefix=" + this.getStripPrefix() + ", uri=" + this.getUri() + ", order=" + this.getOrder() + ")";
    }
}
