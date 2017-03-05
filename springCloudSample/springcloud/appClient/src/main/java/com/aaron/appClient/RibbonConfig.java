package com.aaron.appClient;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * Created by aaron.qiu on 2017/3/5.
 */
public class RibbonConfig {
    @Autowired
    IClientConfig ribbonClientConfig;

    @Bean
    public IPing ribbonPing (IClientConfig config) {
        return new PingUrl();//we override default Iping which is a NoOpPing
    }

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new AvailabilityFilteringRule(); // we override the default ZoneAvoidanceRule
    }

}
