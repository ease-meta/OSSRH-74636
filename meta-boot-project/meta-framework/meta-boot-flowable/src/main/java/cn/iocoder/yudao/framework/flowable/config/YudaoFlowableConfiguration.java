package cn.iocoder.yudao.framework.flowable.config;

import cn.iocoder.yudao.framework.flowable.core.web.FlowableWebFilter;
import io.github.meta.ease.common.enums.WebFilterOrderEnum;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YudaoFlowableConfiguration {

    /**
     * 配置 flowable Web 过滤器
     */
    //@Bean
    public FilterRegistrationBean<FlowableWebFilter> flowableWebFilter() {
        FilterRegistrationBean<FlowableWebFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FlowableWebFilter());
        registrationBean.setOrder(WebFilterOrderEnum.FLOWABLE_FILTER);
        return registrationBean;
    }
}
