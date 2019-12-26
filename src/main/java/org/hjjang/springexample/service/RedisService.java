package org.hjjang.springexample.service;



import org.hjjang.springexample.domain.PersonProfileVo;

import java.util.Map;


public interface RedisService {

    public PersonProfileVo getPersonData(String userName);
    public String setPersonData(Map<String, Object> params);
}
