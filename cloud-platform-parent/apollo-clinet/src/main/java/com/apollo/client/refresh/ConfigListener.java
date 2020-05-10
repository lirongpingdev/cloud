package com.apollo.client.refresh;

import javax.annotation.Resource;

import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;

import com.apollo.client.bean.DevConfig;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

/**
 * 刷新配置
 * @author hp
 *
 */
@Component
public class ConfigListener {
	
	@Resource
	private DevConfig devConfig;
	
	@Resource
	private RefreshScope  refreshScope;
	
	@ApolloConfigChangeListener({"wowo"})
	public void onChange(ConfigChangeEvent configChangeEvnet) {
		boolean configChange =false;
		for(String key : configChangeEvnet.changedKeys()) {
			if(key.startsWith("wo")) {
				configChange =true;
				break;
			}
		}
		
		if(!configChange) {
			return ;
		}
		
		refreshScope.refresh("devConfig");
	}
	
}
