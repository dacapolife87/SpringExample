package org.hjjang.springexample.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class ValueAnnotationExample {

    @Value("${example.value.order}")
    private final String initValue = "5000";

    @PostConstruct
    public void printInitValue(){
        log.info("InitValue : {}",initValue);
    }
}
