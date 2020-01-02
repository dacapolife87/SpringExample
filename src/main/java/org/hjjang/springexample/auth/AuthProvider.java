package org.hjjang.springexample.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hjjang.springexample.domain.AuthUserInfo;
import org.hjjang.springexample.domain.UserInfo;
import org.hjjang.springexample.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthProvider implements AuthenticationProvider {

    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserInfo userInfo = userService.getUserInfo(id);
        if(userInfo == null || !password.equals(userInfo.getUserPassword())){
            return null;
        }

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        if(userInfo.isAdmin()){
            grantedAuthorityList.add(new SimpleGrantedAuthority(AuthConst.ROLE_TYPE.ROLE_ADMIN.toString()));
        }else{
            grantedAuthorityList.add(new SimpleGrantedAuthority(AuthConst.ROLE_TYPE.ROLE_USER.toString()));
        }

        return new AuthUserInfo(id, password, grantedAuthorityList, userInfo);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
