package org.hjjang.springexample.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("person")
public class PersonProfileVo implements Serializable {

    @Id
    String userName;

    String userPhoneNum;

    String userEmail;

}
