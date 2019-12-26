package org.hjjang.springexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hjjang.springexample.domain.PersonProfileVo;
import org.hjjang.springexample.repository.RedisRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{

    private final RedisRepository redisRepository;

    @Override
    public PersonProfileVo getPersonData(String userName) {
        Optional<PersonProfileVo> byId = redisRepository.findById(userName);
        PersonProfileVo personProfileVo = byId.orElseThrow(() -> new IllegalArgumentException());
        return personProfileVo;
    }

    @Override
    public String setPersonData(Map<String, Object> params) {
        log.info("InsertData!");
        String userName = (String) params.get("name");
        String userPhoneNum = (String) params.get("phone");
        String userEmail = (String) params.get("email");

        PersonProfileVo personProfileVo = new PersonProfileVo();
        personProfileVo.setUserName(userName);
        personProfileVo.setUserPhoneNum(userPhoneNum);
        personProfileVo.setUserEmail(userEmail);
        PersonProfileVo save = redisRepository.save(personProfileVo);
        log.info("InsertData Done!");
        return save.getUserName();
    }
}
