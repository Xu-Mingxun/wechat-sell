package com.mingxun.repository;

import com.mingxun.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    // 根据用户的openid，分页查订单信息
    // 如果不分页，就会查到所有的订单，太多了
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
