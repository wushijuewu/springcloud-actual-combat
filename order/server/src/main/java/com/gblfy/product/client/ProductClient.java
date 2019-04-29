package com.gblfy.product.client;//package com.gblfy.order.client;
//
//import com.gblfy.product.common.DecreaseStockInput;
//import com.gblfy.product.common.ProductInfoOutput;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.List;
//
///**
// * @author gblfy
// * @ClassNme ProductClient
// * @Description 服务降级
// * @Date 2019/4/22 22:11
// * @version1.0
// */
///*
//    实现思路：
//    1.在@FeignClient注解中添加fallback字段,指定触发服务降级后指向降级后类的方法
//    2.在服务降级类上添加@Component注解
// */
//@FeignClient(name = "product", fallback = ProductClient. ProductClientFallback.class)
//public interface ProductClient {
//
//    @PostMapping("/product/listForOrder")
//    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);
//
//    @PostMapping("/product/decreaseStock")
//    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
//
//    @Component
//    static class ProductClientFallback implements ProductClient {
//
//        @Override
//        public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
//            return null;
//        }
//
//        @Override
//        public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
//
//        }
//    }
//
//}
