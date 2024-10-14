package com.mingxun.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: wechat-sell
 * @description: 商品表单
 * @author: xu-mingxun
 * @create: 2024-10-13 14:57
 **/
@Data
public class ProductForm {

    private String productId;

    /* 名字*/
    private String productName;

    /* 单价*/
    private BigDecimal productPrice;

    /* 库存*/
    private Integer productStock;

    /* 描述*/
    private String productDescription;

    /* 小图*/
    private String productIcon;

    /* 类目编号*/
    private Integer categoryType;
}
