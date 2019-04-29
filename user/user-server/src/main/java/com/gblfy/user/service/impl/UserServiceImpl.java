package com.gblfy.user.service.impl;

import com.gblfy.user.dataobject.UserInfo;
import com.gblfy.user.repository.UserInfoRepository;
import com.gblfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/27 15:27
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 通过openid来查询用户
     *
     * @param openid
     * @return
     */
    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
