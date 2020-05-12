package com.cloud.platform.producer.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class UserContext extends User {

	private static final long serialVersionUID = 3506027040646306835L;
	
	private String userId;// 用户id
    private String realName;// 用户姓名
    private String telephone;// 手机号
    private UserType userType;

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public UserContext(String realName, String password, Collection<? extends GrantedAuthority> authorities) {
        super(realName, password, authorities);
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
