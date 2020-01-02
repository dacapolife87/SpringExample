package org.hjjang.springexample.domain;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
public class AuthUserInfo extends UsernamePasswordAuthenticationToken {

    String userId;
    UserInfo user;

    public AuthUserInfo(String id, String password, List<GrantedAuthority> grantedAuthorityList, UserInfo userInfo) {
        super(id, password, grantedAuthorityList);
        this.userId = id;
        this.user = userInfo;
    }
}
