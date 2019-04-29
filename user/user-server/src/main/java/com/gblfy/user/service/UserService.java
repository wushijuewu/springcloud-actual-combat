package com.gblfy.user.service;

import com.gblfy.user.dataobject.UserInfo;

/**
 * @ClassName UserService
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/27 15:25
 * @Version 1.0
 **/
public interface UserService {

    /**
     * 通过openid来查询用户
     *
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);
}
