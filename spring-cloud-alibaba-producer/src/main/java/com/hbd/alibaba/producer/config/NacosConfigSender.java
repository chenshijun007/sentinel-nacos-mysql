package com.hbd.alibaba.producer.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;

public class NacosConfigSender {

    public static void main(String[] args) throws Exception {
        final String remoteAddress = "192.168.99.125:8848";
        final String groupId = "hbd_rule";
        final String dataId = "producer_system_rule.yml";
        final String rule = "[\n"
            + "  {\n"
            + "    \"avgLoad\": -1,\n"
            + "    \"avgRt\": 1,\n"
            + "    \"maxThread\": -1,\n"
            + "    \"qps\": -1,\n"
            + "  }\n"
            + "]";
        ConfigService configService = NacosFactory.createConfigService(remoteAddress);
        System.out.println(configService.publishConfig(dataId, groupId, rule));
    }
}