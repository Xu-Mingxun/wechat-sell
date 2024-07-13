package com.mingxun.repository;

import com.mingxun.dataobject.OrderDetail;
import java.math.BigDecimal;
import java.util.List;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12345619");
        orderDetail.setOrderId("111220");
        orderDetail.setProductIcon("https://44mm.jpg");
        orderDetail.setProductId("202");
        orderDetail.setProductName("电视");
        orderDetail.setProductPrice(new BigDecimal("1.2"));
        orderDetail.setProductQuantity(2);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    void findByOrderId() {
        List<OrderDetail> orderDetails = repository.findByOrderId("111220");
        System.out.println(orderDetails);
        Assert.assertNotEquals(0, orderDetails.size());
    }
}