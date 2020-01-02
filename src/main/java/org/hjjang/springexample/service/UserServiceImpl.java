package org.hjjang.springexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hjjang.springexample.domain.UserInfo;
import org.hjjang.springexample.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public void joinExampleUser() {

    }

    @Override
    public UserInfo getUserInfo(String userId) {
        Optional<UserInfo> one = userInfoRepository.findById(userId);
        if(one.isPresent()){
            return one.get();
        }
        return null;
    }
}
