## 1.`FeignClientsRegistrar`

根据注解上`FeignClient`、`EnableFeignClients`属性注入容器

### 1.1 注入`FeignClientSpecification`

### 1.2 注入`FeignClientFactoryBean`

## 2.注入`FeignClientFactoryBean`

### 2.1 获取feign接口代理类

```java
<T> T getTarget() {
    //获取FeignContexts上下文，如果是链路跟踪会获取到TraceFeignContext。默认是通过FeignAutoConfiguration注入FeignContext；链路跟踪通过TraceFeignClientAutoConfiguration注入FeignContextBeanPostProcessor在postProcessAfterInitialization之后将FeignContext修改为TraceFeignContext，并且将FeignContext依赖在自己的对象内部
	FeignContext context = beanFactory != null ? beanFactory.getBean(FeignContext.class)
			: applicationContext.getBean(FeignContext.class);
    //生成Feign.Builder
	Feign.Builder builder = feign(context);
	//如果url没有属性然后根据name生成客户端url
	if (!StringUtils.hasText(url)) {
		if (!name.startsWith("http")) {
			url = "http://" + name;
		}
		else {
			url = name;
		}
		url += cleanPath();
        //生成客户端负载均衡器
		return (T) loadBalance(builder, context, new HardCodedTarget<>(type, name, url));
	}
	if (StringUtils.hasText(url) && !url.startsWith("http")) {
		url = "http://" + url;
	}
	String url = this.url + cleanPath();
	Client client = getOptional(context, Client.class);
	if (client != null) {
		if (client instanceof FeignBlockingLoadBalancerClient) {
			// not load balancing because we have a url,
			// but Spring Cloud LoadBalancer is on the classpath, so unwrap
			client = ((FeignBlockingLoadBalancerClient) client).getDelegate();
		}
		builder.client(client);
	}
	Targeter targeter = get(context, Targeter.class);
	return (T) targeter.target(this, builder, context, new HardCodedTarget<>(type, name, url));
}
```
```java
protected Feign.Builder feign(FeignContext context) {
       //通过context.getInstance(contextId, type);根据客户端生成跟客户端关联的spring容器，并且注入不同bean（bean包含在FeignClientsConfiguration）
		FeignLoggerFactory loggerFactory = get(context, FeignLoggerFactory.class);
		Logger logger = loggerFactory.create(type);

		// @formatter:off
		Feign.Builder builder = get(context, Feign.Builder.class)
				// required values
				.logger(logger)
				.encoder(get(context, Encoder.class))
				.decoder(get(context, Decoder.class))
				.contract(get(context, Contract.class));
		// @formatter:on
		//获取FeignClientProperties feign属性如超时时间等配置
		configureFeign(context, builder);
    	//FeignBuilderCustomizer 客户化修改Feign.Builder
		applyBuildCustomizers(context, builder);

		return builder;
	}
```

生成负载均衡客户端(T) loadBalance(builder, context, new HardCodedTarget<>(type, name, url));

```java
protected <T> T loadBalance(Feign.Builder builder, FeignContext context, HardCodedTarget<T> target) {
    	//FeignLoadBalancerAutoConfiguration,1.生成FeignBlockingLoadBalancerClient；2.生成RetryableFeignBlockingLoadBalancerClient。但是如果加入了链路跟踪都会被FeignContextBeanPostProcessor修改为TraceFeignBlockingLoadBalancerClient、TraceRetryableFeignBlockingLoadBalancerClient
		Client client = getOptional(context, Client.class);
		if (client != null) {
			builder.client(client);
            //Targer默认是DefaultTargeter，在FeignAutoConfiguration注入
			Targeter targeter = get(context, Targeter.class);
            //最终代理客户端FeignInvocationHandler
			return targeter.target(this, builder, context, target);
		}

		throw new IllegalStateException(
				"No Feign Client for loadBalancing defined. Did you forget to include spring-cloud-starter-loadbalancer?");
	}
```

`NamedContextFactory`类

```java
protected AnnotationConfigApplicationContext createContext(String name) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		if (this.configurations.containsKey(name)) {
			for (Class<?> configuration : this.configurations.get(name).getConfiguration()) {
				context.register(configuration);
			}
		}
		for (Map.Entry<String, C> entry : this.configurations.entrySet()) {
			if (entry.getKey().startsWith("default.")) {
				for (Class<?> configuration : entry.getValue().getConfiguration()) {
					context.register(configuration);
				}
			}
		}
		//
		context.register(PropertyPlaceholderAutoConfiguration.class, this.defaultConfigType);
		//注入feign.client.name
		context.getEnvironment().getPropertySources().addFirst(new MapPropertySource(this.propertySourceName,
				Collections.<String, Object>singletonMap(this.propertyName, name)));
		if (this.parent != null) {
			// Uses Environment from parent as well as beans
			context.setParent(this.parent);
			// jdk11 issue
			// https://github.com/spring-cloud/spring-cloud-netflix/issues/3101
			context.setClassLoader(this.parent.getClassLoader());
		}
		context.setDisplayName(generateDisplayName(name));
		context.refresh();
		return context;
	}
```

### 2.2 执行类`SynchronousMethodHandler`

```java
 @Override
  public Object invoke(Object[] argv) throws Throwable {
    RequestTemplate template = buildTemplateFromArgs.create(argv);
    Options options = findOptions(argv);
      //一般不需要retry尝试机制，对业务幂等等机制要求太高
    Retryer retryer = this.retryer.clone();
    while (true) {
      try {
        return executeAndDecode(template, options);
      } catch (RetryableException e) {
        try {
          retryer.continueOrPropagate(e);
        } catch (RetryableException th) {
          Throwable cause = th.getCause();
          if (propagationPolicy == UNWRAP && cause != null) {
            throw cause;
          } else {
            throw th;
          }
        }
        if (logLevel != Logger.Level.NONE) {
          logger.logRetry(metadata.configKey(), logLevel);
        }
        continue;
      }
    }
  }
```

生成调用类

```java
@Override
	public Response execute(Request request, Request.Options options) throws IOException {
        //获取URL
		final URI originalUri = URI.create(request.url());
		//获取服务ID
        String serviceId = originalUri.getHost();
		Assert.state(serviceId != null, "Request URI does not contain a valid hostname: " + originalUri);
        //获取命中值
		String hint = getHint(serviceId);
		DefaultRequest<RequestDataContext> lbRequest = new DefaultRequest<>(
				new RequestDataContext(buildRequestData(request), hint));
         //获取符合当前请求实例的生命周期管理对象，一般作为回调在服务执行前、后做一些处理
		Set<LoadBalancerLifecycle> supportedLifecycleProcessors = LoadBalancerLifecycleValidator
				.getSupportedLifecycleProcessors(
						loadBalancerClientFactory.getInstances(serviceId, LoadBalancerLifecycle.class),
						RequestDataContext.class, ResponseData.class, ServiceInstance.class);
        //调用生命 周期onStart回调方法
		supportedLifecycleProcessors.forEach(lifecycle -> lifecycle.onStart(lbRequest));
          //获取请求实例对应的微服务实例对象BlockingLoadBalancerClient
		ServiceInstance instance = loadBalancerClient.choose(serviceId, lbRequest);
         //请求响应对象
		org.springframework.cloud.client.loadbalancer.Response<ServiceInstance> lbResponse = new DefaultResponse(
				instance);
          //如果请求实例不存在，则直接调用生命周期完成方法onComplete
		if (instance == null) {
			String message = "Load balancer does not contain an instance for the service " + serviceId;
			if (LOG.isWarnEnabled()) {
				LOG.warn(message);
			}
			supportedLifecycleProcessors.forEach(lifecycle -> lifecycle
					.onComplete(new CompletionContext<ResponseData, ServiceInstance, RequestDataContext>(
							CompletionContext.Status.DISCARD, lbRequest, lbResponse)));
			return Response.builder().request(request).status(HttpStatus.SERVICE_UNAVAILABLE.value())
					.body(message, StandardCharsets.UTF_8).build();
		}
        //获取实例请求的真实请求对象
		String reconstructedUrl = loadBalancerClient.reconstructURI(instance, originalUri).toString();
         //构建Request请求对象
		Request newRequest = buildRequest(request, reconstructedUrl);
           //执行真实请求
		return executeWithLoadBalancerLifecycleProcessing(delegate, options, newRequest, lbRequest, lbResponse,
				supportedLifecycleProcessors);
	}
```

```java
@Override
	public <T> ServiceInstance choose(String serviceId, Request<T> request) {
        //LoadBalancerClientFactory获取ReactorServiceInstanceLoadBalancer。ReactorServiceInstanceLoadBalancer目前有两种实现RandomLoadBalancer和RoundRobinLoadBalancer（默认），在LoadBalancerClientConfiguration配置中实现，LoadBalancerClientConfiguration在LoadBalancerClientFactory，LoadBalancerClientFactory在LoadBalancerAutoConfiguration
		ReactiveLoadBalancer<ServiceInstance> loadBalancer = loadBalancerClientFactory.getInstance(serviceId);
		if (loadBalancer == null) {
			return null;
		}
		Response<ServiceInstance> loadBalancerResponse = Mono.from(loadBalancer.choose(request)).block();
		if (loadBalancerResponse == null) {
			return null;
		}
		return loadBalancerResponse.getServer();
	}
```

`RoundRobinLoadBalancer`

```java
	@Override
	// see original
	// https://github.com/Netflix/ocelli/blob/master/ocelli-core/
	// src/main/java/netflix/ocelli/loadbalancer/RoundRobinLoadBalancer.java
	public Mono<Response<ServiceInstance>> choose(Request request) {
        //通过ServiceInstanceListSupplier对象获取到当前请求所有符合要求的实例，再进行筛选实现负载均衡的。ServiceInstanceListSupplier是通过loadBalancerClientFactory加载,配置在LoadBalancerClientConfiguration，比如ZonePreferenceServiceInstanceListSupplier同区域优先，SameInstancePreferenceServiceInstanceListSupplier和上次实例相同优先
		ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
				.getIfAvailable(NoopServiceInstanceListSupplier::new);
		return supplier.get(request).next()
				.map(serviceInstances -> processInstanceResponse(supplier, serviceInstances));
	}
```

