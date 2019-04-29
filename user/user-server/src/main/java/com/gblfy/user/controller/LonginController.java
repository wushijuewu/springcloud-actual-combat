package com.gblfy.user.controller;

import com.gblfy.user.constant.CookieConstant;
import com.gblfy.user.constant.RedisConstant;
import com.gblfy.user.dataobject.UserInfo;
import com.gblfy.user.enums.ResultEnum;
import com.gblfy.user.enums.RoleEnum;
import com.gblfy.user.service.UserService;
import com.gblfy.user.utils.CookieUtil;
import com.gblfy.user.utils.ResultVOUtil;
import com.gblfy.user.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LonginController
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/27 15:28
 * @Version 1.0
 **/
@RestController
@RequestMapping("/login")
public class LonginController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登陆
     *
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response) {
        //1.openid和数据库里的数据是否区配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        //2.判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        //3.cookie里设置openid=abc
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);

        return ResultVOUtil.success();
    }
    //测试链接 http://localhost:8088/login/buyer?openid=abc

    /**
     * 卖家登陆
     *
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                           HttpServletResponse response,
                           HttpServletRequest request) {
        /*
        前提 判断是否已登录  先去查询cookie是否存在，
        然后把cookie当做key去redis中，取value的值，
        如果存在，表示已经登录，reids就不会存储多条用户(同一)已经登录的信息
         */
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null &&
                !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            return ResultVOUtil.success();
        }

        //1.openid和数据库里的数据是否区配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        //2.判断角色
        if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        //3.redis设置key=UUID value=xyz
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openid, expire, TimeUnit.SECONDS);

        //4.cookie里设置token=UUID
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);

        return ResultVOUtil.success();
        //测试链接 http://localhost:8088/login/seller?openid=xyz
    }

}
