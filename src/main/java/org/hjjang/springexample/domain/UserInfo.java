package org.hjjang.springexample.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserInfo {

    @Id
    String userId;

    String userPassword;

    boolean isAdmin;
}
