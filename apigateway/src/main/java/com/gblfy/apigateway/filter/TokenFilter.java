//package com.gblfy.apigateway.filter;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.HttpStatus;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @ClassName TokenFilter
// * @Deacription TODO
// * @Author gblfy
// * @Date 2019/4/27 12:42
// * @Version 1.0
// **/
//public class TokenFilter extends ZuulFilter {
//
//    @Override
//    public String filterType() {
//        return FilterConstants.PRE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER - 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() {
//        RequestContext requestContext = new RequestContext();
//        HttpServletRequest request = requestContext.getRequest();
//
//        //这里从url参数里获取，也可以从cookie，header里获取
//        String token = request.getParameter("token");
//        if(StringUtils.isEmpty(token)){
//            requestContext.setSendZuulResponse(false);
//            requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
//        }
//
//        return null;
//    }
//}
