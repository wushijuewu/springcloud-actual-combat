//package com.gblfy.apigateway.filter;
//
//import RedisConstant;
//import CookieUtil;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.HttpStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
//
///**
// * 权限拦截
// * 区分买家和卖家
// */
//@Component
//public class AuthFilter extends ZuulFilter {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Override
//    public String filterType() {
//        return PRE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return PRE_DECORATION_FILTER_ORDER - 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = requestContext.getRequest();
//
//        /**
//         * /order/create 创建订单只能买家访问(cookie里有openid)
//         * /order/finish 完结订单只能卖家访问(cookie里有token,并且redis里有值)
//         * /product/list 都能访问
//         */
//        if ("/order/order/create".equals(request.getRequestURI())) {
//            Cookie cookie = CookieUtil.get(request, "orderId");
//            if (cookie == null || StringUtils.isEmpty(cookie.getValue())) {
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
//            }
//        }
//        if ("/order/order/finish".equals(request.getRequestURI())) {
//            Cookie cookie = CookieUtil.get(request, "token");
//            if (cookie == null
//                    || StringUtils.isEmpty(cookie.getValue())
//                    || StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
//            }
//        }
//        return null;
//    }
//}
