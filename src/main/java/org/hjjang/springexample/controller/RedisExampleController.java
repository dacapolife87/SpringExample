package org.hjjang.springexample.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hjjang.springexample.domain.PersonProfileVo;
import org.hjjang.springexample.service.RedisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class RedisExampleController {

    private final RedisService redisService;

    @RequestMapping(value = "/redis/user/{userName}",method = RequestMethod.GET)
    public @ResponseBody PersonProfileVo getPersonProfiles(@PathVariable(value = "userName") String userName){

        return redisService.getPersonData(userName);
    }

    @RequestMapping(value = "/redis/user",method = RequestMethod.GET)
    public @ResponseBody String setPersonProfiles(@RequestParam Map<String,Object> params){
        log.info("Insert Profile!");
        String s = redisService.setPersonData(params);
        log.info("Done!");
        return s;
    }

}
