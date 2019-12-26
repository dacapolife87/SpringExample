package org.hjjang.springexample.controller;

import lombok.RequiredArgsConstructor;
import org.hjjang.springexample.service.RestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RestExampleController {

    private final RestService restService;

    @RequestMapping(value = "/rest/get", method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> getRestController(){
        Map<String, Object> returnMap = null;
        try {
            returnMap = restService.restTemplateGetExample();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value = "/rest/post", method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> postRestController(){
        Map<String, Object> returnMap = null;
        try {
            returnMap = restService.restTemplatePostExample();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnMap;
    }
}
