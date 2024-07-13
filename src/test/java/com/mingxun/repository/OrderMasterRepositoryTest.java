package com.mingxun.repository;

import com.mingxun.dataobject.OrderMaster;
import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.Date;
import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "110022";

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("小兰");
        orderMaster.setBuyerPhone("13171511158");
        orderMaster.setBuyerAddress("地球");
        orderMaster.setBuyerOpenid("110022");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotEquals(null, result);
    }

    @Test
    void findByBuyerOpenid() throws Exception{
        PageRequest request = PageRequest.of(1, 3);
        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, request);
        Assert.assertNotEquals(0, result.getTotalElements());
    }
}