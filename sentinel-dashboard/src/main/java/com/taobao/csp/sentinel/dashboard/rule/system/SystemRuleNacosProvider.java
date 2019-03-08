package com.taobao.csp.sentinel.dashboard.rule.system;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.nacos.api.config.ConfigService;
import com.taobao.csp.sentinel.dashboard.config.NacosConfigUtil;
import com.taobao.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.taobao.csp.sentinel.dashboard.rule.DynamicRuleProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("systemRuleNacosProvider")
public class SystemRuleNacosProvider implements DynamicRuleProvider<List<SystemRuleEntity>> {

    @Autowired
    private ConfigService configService;
    @Autowired
    private Converter<String, List<SystemRuleEntity>> convert;

    @Override
    public List<SystemRuleEntity> getRules(String appName) throws Exception {
        String rules = configService.getConfig(appName + NacosConfigUtil.SYSTEM_DATA_ID_POSTFIX,
            NacosConfigUtil.GROUP_ID, 3000);
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        return convert.convert(rules);
    }
}
