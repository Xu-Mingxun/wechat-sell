package com.mingxun.controller;

import com.mingxun.dataobject.ProductCategory;
import com.mingxun.exception.SellException;
import com.mingxun.form.CategoryForm;
import com.mingxun.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @program: wechat-sell
 * @description: 卖家类目
 * @author: xu-mingxun
 * @create: 2024-10-13 17:08
 **/
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("/category/list", map);
    }

    @GetMapping("/index")
    ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                       Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("category", productCategory);
            return new ModelAndView("category/index", map);
        }
        return new ModelAndView("category/index", map);
    }

    @PostMapping("/save")
    ModelAndView save(@Valid CategoryForm categoryForm,
                      BindingResult bindingResult,
                      Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/wechat-sell/seller/category/list");
            return new ModelAndView("/common/error", map);
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            if (categoryForm.getCategoryId() != null) {
                productCategory = categoryService.findOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm, productCategory);
            categoryService.save(productCategory);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/wechat-sell/seller/category/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/wechat-sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
