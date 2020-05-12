package com.cloud.platform.producer.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.cloud.platform.producer.bean.User;
import com.cloud.platform.producer.security.AuthoritiesConstants;
import com.cloud.platform.producer.security.TempUserAuthenticationToken;
import com.cloud.platform.producer.security.TempWechatInfo;
import com.cloud.platform.producer.security.UserContext;
import com.cloud.platform.producer.security.jwt.TokenProvider;

@Service
public class ProducerServiceImpl implements IProducerSrvice {

	@Autowired
	private TokenProvider tokenProvider;
	
	public Authentication createAuthentication(TempWechatInfo info) {
        List<GrantedAuthority> grantedAuthorities = Arrays.stream(new SimpleGrantedAuthority[]{new SimpleGrantedAuthority(AuthoritiesConstants.USER)}).collect(Collectors.toList());
        UserContext context = new UserContext(info.getNickname(), "", grantedAuthorities);
        context.setUserId(info.getOpen_id());
        context.setRealName(info.getNickname());
        Authentication authentication = new TempUserAuthenticationToken(context, null, grantedAuthorities);
        return authentication;
    }
	
	@Override
	public String getToken() {
		TempWechatInfo tempWechatInfo = new TempWechatInfo();
		tempWechatInfo.setNickname("nickname123");
		tempWechatInfo.setOpen_id("open_id123");
		return tokenProvider.createToken(createAuthentication(tempWechatInfo),true);
	}

	@Override
	public User getUsers() {
		return new User(100+1, "userName"+1, "100000000"+1, "address"+1);
	}

}
