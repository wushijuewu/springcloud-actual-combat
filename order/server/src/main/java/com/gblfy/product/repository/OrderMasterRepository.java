package com.gblfy.product.repository;

import com.gblfy.product.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName OrderMasterRepository
 * @Deacription TODO
 * @Author gblfy
 * @Date 2019/4/22 18:04
 * @Version 1.0
 **/
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
}
