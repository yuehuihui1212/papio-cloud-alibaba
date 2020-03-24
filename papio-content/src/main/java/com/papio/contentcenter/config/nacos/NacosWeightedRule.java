package com.papio.contentcenter.config.nacos;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: yuehuihui
 * @Description:
 * @Date: created in 16:25 2020/2/6
 * @Modified By:
 **/
@Slf4j
public class NacosWeightedRule extends AbstractLoadBalancerRule {

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        try {
            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
            String serviceName = loadBalancer.getName();
            log.info("serviceName: {}",serviceName);
            NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
            Instance instance = namingService.selectOneHealthyInstance(serviceName);
            return new NacosServer(instance);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
