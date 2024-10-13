package com.mingxun.controller;

import com.mingxun.dataobject.ProductInfo;
import com.mingxun.exception.SellException;
import com.mingxun.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
}
