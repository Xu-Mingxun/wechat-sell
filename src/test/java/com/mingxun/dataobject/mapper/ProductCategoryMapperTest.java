package com.mingxun.dataobject.mapper;

import com.mingxun.dataobject.ProductCategory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "狗狗最爱");
        map.put("category_type", 101);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory result = mapper.findByCategoryType(102);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    // xml测试
    @Test
    public void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("叔叔最爱");
        productCategory.setCategoryType(105);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectByCategoryType() {
        ProductCategory productCategory = mapper.selectByCategoryType(101);
        System.out.println(productCategory);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void selectByCategoryName() {
        List<ProductCategory> result = mapper.selectByCategoryName("猫猫最爱");
        System.out.println(result);
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void updateByCategoryType() {
        int result = mapper.updateByCategoryType("鼠鼠最爱", 103);
        System.out.println(result);
        Assert.assertNotEquals(0, result);
    }

    @Test
    public void updateByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("Male最爱");
        productCategory.setCategoryType(11);
        int result = mapper.updateByObject(productCategory);
        System.out.println(result);
        Assert.assertNotEquals(0, result);
    }

    @Test
    public void deleteByCategoryType() {
        int result = mapper.deleteByCategoryType(999);
        System.out.println(result);
        Assert.assertNotEquals(0, result);
    }
}