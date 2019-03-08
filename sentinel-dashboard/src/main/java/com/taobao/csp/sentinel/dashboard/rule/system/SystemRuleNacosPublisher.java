package com.taobao.csp.sentinel.dashboard.rule.system;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.nacos.api.config.ConfigService;
import com.taobao.csp.sentinel.dashboard.config.NacosConfigUtil;
import com.taobao.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.taobao.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.taobao.csp.sentinel.dashboard.rule.DynamicRulePublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("systemRuleNacosPublisher")
public class SystemRuleNacosPublisher implements DynamicRulePublisher<List<SystemRuleEntity>> {

    @Autowired
    private ConfigService configService;
    @Autowired
    private Converter<List<Map<String,Object>>,String> convert;


    @Override
    public void publish(String app, List<SystemRuleEntity> rules) throws Exception {
        if (rules == null) {
            return;
        }
        List<Map<String,Object>> ruleList= new ArrayList<>();
        rules.forEach(obj->{
            Map<String,Object> map = new HashMap<>();
            map.put("avgLoad",obj.getAvgLoad());
            map.put("avgRt",obj.getAvgRt());
            map.put("maxThread",obj.getMaxThread());
            map.put("qps",obj.getQps());
            ruleList.add(map);
        });
        configService.publishConfig(app+NacosConfigUtil.SYSTEM_DATA_ID_POSTFIX,NacosConfigUtil.GROUP_ID,convert.convert(ruleList));
    }
}
