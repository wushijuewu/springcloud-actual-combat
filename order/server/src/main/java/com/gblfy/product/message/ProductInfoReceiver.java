package com.gblfy.product.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gblfy.product.ProductInfoOutput;
import com.gblfy.product.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gblfy
 * @ClassNme ProductInfoReceiver
 * @Description TODO
 * @Date 2019/4/26 22:09
 * @version1.0
 */
@Component
@Slf4j
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        //message->>>ProductInfoOutput
        List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>) JsonUtil.fromJson(message,
                new TypeReference<List<ProductInfoOutput>>() {
                });
        log.info("从队列【{}】接收到消息:{}", "productIfo", productInfoOutputList);

        //存储到redis中
        for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId()),
                    String.valueOf(productInfoOutput.getProductStock()));
        }
    }
}
