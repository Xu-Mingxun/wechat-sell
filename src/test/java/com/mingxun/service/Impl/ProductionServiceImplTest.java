package com.mingxun.service.Impl;

import com.mingxun.dataobject.ProductInfo;
import com.mingxun.enums.ProductStatusEnum;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductionServiceImplTest {

    @Autowired
    private ProductionServiceImpl productionService;

    @Test
    void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("大米粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("不好吃");
        productInfo.setProductIcon("https://xxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);

        ProductInfo result = productionService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    void findAll() {
        PageRequest request = PageRequest.of(0, 2);
        Page<ProductInfo> productInfoPage = productionService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());

    }

    @Test
    void findUpAll() {
        List<ProductInfo> productInfoList = productionService.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    void findOne() {
        ProductInfo productInfo = productionService.findOne("123456");
        Assert.assertEquals("123456", productInfo.getProductId());
    }
}