package com.gblfy.user.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName UserInfo
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/27 15:21
 * @Version 1.0
 **/
@Data
@Entity
public class UserInfo {

    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

    private Integer role;
}
