package com.mingxun.service.Impl;

import com.mingxun.dataobject.ProductInfo;
import com.mingxun.enums.ProductStatusEnum;
import java.math.BigDecimal;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Transactional
    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("大米粥");
        productInfo.setProductPrice(new BigDecimal(3.9));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("不好吃");
        productInfo.setProductIcon("https://xxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);

        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0, 2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0, productInfoPage.getTotalPages());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("123457");
        System.out.println(productInfo);
        Assert.assertEquals("123457", productInfo.getProductId());
    }

    @Test
    void onSale() {
        ProductInfo result = productService.onSale("123457");
        Assert.assertEquals(ProductStatusEnum.UP, result.getProductStatusEnum());
    }

    @Test
    void offSale() {
        ProductInfo result = productService.offSale("123457");
        Assert.assertEquals(ProductStatusEnum.DOWN, result.getProductStatusEnum());
    }
}