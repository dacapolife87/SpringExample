package org.hjjang.springexample.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestServiceImpl implements RestService{

    @Value("${example.server.url}")
    private String serverIp;

    @Value("${example.server.port}")
    private String serverPort;

    public Map<String,Object> restTemplateGetExample() throws IOException {
        Map<String,Object> rmap = new HashMap<String,Object>();

        RestTemplate restTemplate = new RestTemplate();

        URI uri = URI.create("http://"+serverIp + ":"+serverPort + "/api/getmapping");

        ResponseEntity<String> result = null;

        try {
            result = restTemplate.getForEntity(uri, String.class);
        } catch (HttpClientErrorException e) {

            log.error("HttpClientErrorException",e);
            result = new ResponseEntity<String>(e.getStatusCode());
        }

        log.info("## HttpReq : {}, StatusCode : {}",uri,result.getStatusCode());
        log.info("Body : {}",result.getBody());


        ObjectMapper mapper = new ObjectMapper();
        rmap = mapper.readValue(result.getBody(), new TypeReference<Map<String, Object>>(){});
        log.info("ReturnMap : {}",rmap);
        return rmap;

    }

    public Map<String,Object> restTemplatePostExample() throws IOException {
        Map<String,Object> rmap = new HashMap<String,Object>();

        RestTemplate restTemplate = new RestTemplate();

        URI uri = URI.create("http://"+serverIp + ":"+serverPort + "/api/postmapping");

        ResponseEntity<String> result = null;

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("REQ1", "reqValue1");
        parameters.add("REQ2", "reqValue2");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(parameters, headers);

        try {
            result =  restTemplate.postForEntity(uri, request, String.class);
        } catch (HttpClientErrorException e) {

            log.error("HttpClientErrorException",e);
            result = new ResponseEntity<String>(e.getStatusCode());
        }

        log.info("## HttpReq : {}, StatusCode : {}",uri,result.getStatusCode());
        log.info("Body : {}",result.getBody());


        ObjectMapper mapper = new ObjectMapper();
        rmap = mapper.readValue(result.getBody(), new TypeReference<Map<String, Object>>(){});
        log.info("ReturnMap : {}",rmap);
        return rmap;

    }

}
