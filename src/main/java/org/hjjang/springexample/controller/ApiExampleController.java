package org.hjjang.springexample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class ApiExampleController {

    @GetMapping(value = "/api/getmapping")
    public @ResponseBody Map<String,Object> getMappingController(){
        Map<String,Object> returnMap = new HashMap<>();

        returnMap.put("status","ok");
        returnMap.put("msg","getMappingController");
        return returnMap;
    }

    @PostMapping(value = "/api/postmapping")
    public @ResponseBody Map<String,Object> postMappingController(@RequestParam Map<String,Object> params){
        log.info("PostMapping [/api/postmapping] Params : ",params);

        Map<String,Object> returnMap = new HashMap<>();

        returnMap.put("status","ok");
        returnMap.put("msg","postMappingController");
        return returnMap;
    }
}
