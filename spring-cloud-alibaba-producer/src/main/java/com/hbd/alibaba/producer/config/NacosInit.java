package com.hbd.alibaba.producer.config;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NacosInit implements InitFunc {
    private static String address;

    @Value("${nacos.address}")
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public void init() throws Exception {
        Converter<String, List<FlowRule>> flowParser = source -> JSON.parseObject(source,new TypeReference<List<FlowRule>>() {});
        ReadableDataSource<String, List<FlowRule>> flowSource = new NacosDataSource<>(address, NacosConfigUtil.GROUPID, NacosConfigUtil.FLOW_RULE, flowParser);
        FlowRuleManager.register2Property(flowSource.getProperty());

        Converter<String, List<SystemRule>> systemParser = source -> JSON.parseObject(source,new TypeReference<List<SystemRule>>() {});
        ReadableDataSource<String, List<SystemRule>> systemSource = new NacosDataSource<>(address, NacosConfigUtil.SYSTEM_RULE, NacosConfigUtil.GROUPID, systemParser);
        SystemRuleManager.register2Property(systemSource.getProperty());
    }
}