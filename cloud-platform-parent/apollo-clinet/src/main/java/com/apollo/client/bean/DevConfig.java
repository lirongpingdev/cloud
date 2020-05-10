package com.apollo.client.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties  //不放前缀的所有属性都能读出来
@ConfigurationProperties(prefix="wo") //用前缀的只能读出以wo开头的属性字段值
//自动刷新配置(要引<artifactId>spring-cloud-context</artifactId>)
@RefreshScope
public class DevConfig {
	private String tel;
	private String email;
	private int sn;
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	@Override
	public String toString() {
		return "DevConfig [tel=" + tel + ", email=" + email + ", sn=" + sn + "]";
	}
}
