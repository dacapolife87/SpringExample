package org.hjjang.springexample.service;

import org.hjjang.springexample.domain.UserInfo;

public interface UserService {

    public void joinExampleUser();
    public UserInfo getUserInfo(String userId);
}
