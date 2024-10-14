package com.mingxun.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.mingxun.dataobject.ProductCategory;
import com.mingxun.dataobject.ProductInfo;
import com.mingxun.exception.SellException;
import com.mingxun.form.ProductForm;
import com.mingxun.service.CategoryService;
import com.mingxun.service.ProductService;
import com.mingxun.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @program: wechat-sell
 * @description: 卖家商品接口
 * @author: xu-mingxun
 * @create: 2024-10-12 02:03
 **/

@Controller
@RequestMapping("seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @ResponseBody
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size, Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productDTOPage = productService.findAll(request);
        map.put("productDTOPage", productDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        // orderDTOPage.getContent();
        return new ModelAndView("product/list", map);
    }

    @GetMapping("on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/wechat-sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "wechat-sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/wechat-sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "wechat-sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }

        // 查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("product/index");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/wechat-sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            // 如果productId不为空，说明不是新增
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId());
            } else {
                form.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/wechat-sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/wechat-sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
