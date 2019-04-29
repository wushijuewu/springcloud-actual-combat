package com.gblfy.product.controller;

import com.gblfy.product.converter.OrderForm2OrderDTOConverter;
import com.gblfy.product.dto.OrderDTO;
import com.gblfy.product.enums.ResultEnum;
import com.gblfy.product.exception.OrderException;
import com.gblfy.product.form.OrderForm;
import com.gblfy.product.service.OrderService;
import com.gblfy.product.utils.ResultVOUtil;
import com.gblfy.product.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OrderController
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/22 18:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     *
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }

    /**
     * 测试工具：postman
     * 请求方式：post
     * 测试链接：http://localhost:8081/order/create
     * 参数：
     * name：张三
     * phone：18868822111
     * address：慕课网总部
     * openid：ew3euwhd7sjw9diwkq
     * items：[{
     *     productId: "164103465734242707",
     *     productQuantity: 20
     * }]
     */

    /**
     * 完结订单
     *
     * @param orderId
     * @return
     */
    @PostMapping("/finish")
    public ResultVO<OrderDTO> finish(@RequestParam("orderId") String orderId) {
        return ResultVOUtil.success(orderService.finish(orderId));

        //测试链接：http://127.0.0.1:8081/order/finish
        /*
        测试工具：postman
        第一次测试属于新订单：
        测试数据：
               orderId 1556101023571201444
         返回数据：
         {
    "code": 0,
    "msg": "成功",
    "data": {
        "orderId": "1556101023571201444",
        "buyerName": "张三",
        "buyerPhone": "18868822111",
        "buyerAddress": "十秒郭家村",
        "buyerOpenid": "ew3euwhd7sjw9diwkq",
        "orderAmount": 0.4,
        "orderStatus": 1,
        "payStatus": 0,
        "orderDetailList": [
            {
                "detailId": "1556101023583328157",
                "orderId": "1556101023571201444",
                "productId": "164103465734242707",
                "productName": "蜜汁鸡翅",
                "productPrice": 0.02,
                "productQuantity": 20,
                "productIcon": "//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg"
            }
        ]
    }
}


第2次测试：
该订单不属于新订单
返回数据如下：

{
    "timestamp": "2019-04-28T02:43:26.248+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "订单状态错误",
    "path": "/order/finish"
}
         */
    }
}
