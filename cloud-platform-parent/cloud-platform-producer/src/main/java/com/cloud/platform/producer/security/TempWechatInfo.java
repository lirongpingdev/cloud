package com.cloud.platform.producer.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TempWechatInfo {
	
    public Long id;

    public String union_id;

    public String open_id;

    public String session_key;

    public String nickname;

    public String gender;

    public String province;

    public String city;

    public Boolean authenticated;

    public String mobile;

    public String account_id;

    public String transaction_pwd;

    public int app_type;

    
}
