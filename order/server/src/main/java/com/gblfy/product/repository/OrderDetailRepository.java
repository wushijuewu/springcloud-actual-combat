package com.gblfy.product.repository;

import com.gblfy.product.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName OrderDetailRepository
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/22 18:04
 * @Version 1.0
 **/
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String orderId);

}
