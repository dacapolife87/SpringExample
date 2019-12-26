package org.hjjang.springexample.service;

import java.io.IOException;
import java.util.Map;

public interface RestService {
    public Map<String,Object> restTemplateGetExample() throws IOException;
    public Map<String,Object> restTemplatePostExample() throws IOException;
}
