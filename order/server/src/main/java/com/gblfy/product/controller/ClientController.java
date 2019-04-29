//package com.gblfy.order.controller;
//
//import com.gblfy.product.client.ProductClient;
//import com.gblfy.order.dataobject.ProductInfo;
//import com.gblfy.order.dto.CartDTO;
//import com.gblfy.product.client.ProductClient;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author gblfy
// * @ClassNme ClientController
// * @Description TODO
// * @Date 2019/4/22 22:14
// * @version1.0
// */
//@RestController
//@Slf4j
//public class ClientController {
//
//
//    @Autowired
//    private ProductClient productClient;
//
//    /**
//     * 调用商品服务测试
//     *
//     * @return
//     */
//    @GetMapping("/getProductMsg")
//    public String getProductMsg() {
//        String reponse = productClient.productMsg();
//        log.info("reponse={}", reponse);
//        return reponse;
//        //测试链接：http://localhost:8081/getProductMsg
//    }
//
//    /**
//     * 查询商品列表
//     *
//     * @return
//     */
//    @GetMapping("/getProductList")
//    public String getProductList() {
//        List<ProductInfo> productInfoList =
//                productClient.listForOrder(Arrays.asList("164103465734242707"));
//        log.info("response={}", productInfoList);
//        return "ok";
//        //测试链接：http://localhost:8081/getProductList
//
//    }
//
//    /**
//     * 扣库存
//     *
//     * @return
//     */
//    @GetMapping("/productDecreaseStock")
//    public String productDecreaseStock() {
//        productClient.decreaseStock(Arrays.asList(new CartDTO("164103465734242707", 3)));
//        return "ok";
//        //测试链接：  http://localhost:8081/productDecreaseStock
//
//    }
//
//}
