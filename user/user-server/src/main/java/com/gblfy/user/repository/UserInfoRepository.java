package com.gblfy.user.repository;

import com.gblfy.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UserInfoRepository
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/27 15:23
 * @Version 1.0
 **/
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);

}
