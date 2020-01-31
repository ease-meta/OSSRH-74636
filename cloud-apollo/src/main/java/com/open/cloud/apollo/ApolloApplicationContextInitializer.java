package com.open.cloud.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.google.common.base.Strings;
import com.open.cloud.apollo.config.ApolloConstant;
import com.open.cloud.apollo.config.ApolloProperties;
import com.open.cloud.common.utils.ClassPoolUtils;
import com.open.cloud.common.utils.LogUtils;
import com.open.cloud.common.utils.PropertyUtils;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.File;

@Order(-1)
public class ApolloApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private static boolean isload = false;

	@Override
	public void initialize(ConfigurableApplicationContext context) {
		val apolloProperties = context.getBean(ApolloProperties.class);
		ConfigurableEnvironment environment = context.getEnvironment();
		if (!apolloProperties.isEnabled()) {
			return;
		}
		this.initializeSystemProperty(context);
	}

	void initializeSystemProperty(ConfigurableApplicationContext context) {
		val apolloProperties = context.getBean(ApolloProperties.class);
		String appId = apolloProperties.getAppId();
		ConfigurableEnvironment environment = context.getEnvironment();

		if (Strings.isNullOrEmpty(appId) || !apolloProperties.isBootstrapEnabled()) {
			return;
		}
		//默认设置app.id
		PropertyUtils.setDefaultInitProperty(ApolloApplicationContextInitializer.class, ApolloConstant.Project, ApolloConstant.AppId, apolloProperties.getAppId());
		if (!StringUtils.isEmpty(apolloProperties.getAppId())) {
			//val env = environment.getProperty(ApolloConstant.Env);
			//默认设置apollo.meta
			//setDefaultProperty(ApolloProperties.ApolloMeta, Environment.dev.toString().equalsIgnoreCase(env) ? BsfEnvironmentEnum.APOLLO_DEV.getUrl() : BsfEnvironmentEnum.APOLLO_PRD.getUrl());
			//默认设置env
			//setDefaultProperty(ApolloProperties.Env, env);
			//默认设置 apollo.bootstrap.enabled=true
			setDefaultProperty(ApolloConstant.ApolloBootstrapEnabled, "true");
			//默认设置 namespaces
			setDefaultProperty(ApolloConstant.ApolloBootstrapNamespaces, "application");
			//默认设置 日志加载前后 v1.2版本+ 的客户端才生效
			setDefaultProperty(ApolloConstant.ApolloBootstrapEagerLoadEnabled, "true");
			//默认config cache 位置
			String configdir = System.getProperty(apolloProperties.getUserDir()) + File.separator + "apolloConfig" + File.separator;
			setDefaultProperty(ApolloConstant.ApolloCacheDir, configdir);
			this.replaceCatInit(environment);
			this.registerConfigChangedListener(environment);
		}

	}

	private void replaceCatInit(ConfigurableEnvironment environment) {
		try {
			ClassPool classPool = ClassPoolUtils.getInstance();
			CtClass ctClass = classPool.get("com.ctrip.framework.apollo.tracer.internals.DefaultMessageProducerManager");
			if (!isload) {
				isload = true;
				CtConstructor[] constructors = ctClass.getConstructors();
				if (constructors != null && constructors.length > 0) {
					CtConstructor constructor = constructors[0];
					constructor.setBody(newMethodCode());
				}
				if (ctClass.isFrozen()) {
					ctClass.defrost();
				}
				ctClass.toClass();
				LogUtils.info(ApolloApplicationContextInitializer.class, ApolloConstant.Project, "重写cat init ok");
			}
		} catch (Exception exp) {
			LogUtils.error(ApolloApplicationContextInitializer.class, ApolloConstant.Project, "重写cat init 异常", exp);
		}
	}

	private void registerConfigChangedListener(ConfigurableEnvironment environment) {
		for (val namespace : environment.getProperty(ApolloConstant.ApolloBootstrapNamespaces, "").split(",")) {
			if (!StringUtils.isEmpty(namespace)) {
				Config config = ConfigService.getConfig(namespace);
				config.addChangeListener(changeEvent -> {
					for (String key : changeEvent.changedKeys()) {
						ConfigChange change = changeEvent.getChange(key);
						LogUtils.info(ApolloApplicationContextInitializer.class, ApolloConstant.Project,
								String.format("监听到apollo配置修改,key: %s, oldValue: %s, newValue: %s, changeType: %s, 当前配置值: %s",
										change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType(), PropertyUtils.getProperty(key)));
						//PropertyCache.Default.tryUpdateCache(change.getPropertyName(), PropertyUtils.getProperty(key));
					}
				});
			}
		}
	}

	private String newMethodCode() {
		String code = "{" +
				"     producer = new com.ctrip.framework.apollo.tracer.internals.NullMessageProducerManager().getProducer();" +
				"}";
		return code;
	}

	void setDefaultProperty(String key, String defaultPropertyValue) {
		PropertyUtils.setDefaultInitProperty(ApolloApplicationContextInitializer.class, ApolloConstant.Project, key, defaultPropertyValue);
	}

}
