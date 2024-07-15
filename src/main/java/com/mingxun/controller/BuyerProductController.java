package com.mingxun.controller;

import com.mingxun.VO.ProductInfoVO;
import com.mingxun.VO.ProductVO;
import com.mingxun.VO.ResultVO;
import com.mingxun.dataobject.ProductCategory;
import com.mingxun.dataobject.ProductInfo;
import com.mingxun.service.CategoryService;
import com.mingxun.service.Impl.ProductServiceImpl;
import com.mingxun.utils.ResultVOResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    private final ProductServiceImpl productServiceImpl;

    public BuyerProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product", key = "#sellerId")
    public ResultVO list(@RequestParam("sellerId") String sellerId) {
        //1. 查询所有的上架商品
        List<ProductInfo> productInfoList = productServiceImpl.findUpAll();

        //2. 查询类目(一次性查询)


        // 传统方法
//        List<Integer> categoryTypeList = new ArrayList<>();
//        for (ProductInfo productInfo : productInfoList) {
//            categoryTypeList.add(productInfo.getCategoryType());
//        }

        // 简洁方法
        List<Integer> categoryTypeList =  productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategorytType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    // 简洁的使用BeanUtil进行拷贝
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOResult.success(productVOList);
    }

    @GetMapping("/deleteCache")
    @CacheEvict(cacheNames = "product", key = "123")
    public void deleteCache() {

    }

}
