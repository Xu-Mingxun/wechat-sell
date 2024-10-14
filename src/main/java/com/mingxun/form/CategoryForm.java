package com.mingxun.form;

import lombok.Data;

/**
 * @program: wechat-sell
 * @description: 商品类目表单
 * @author: xu-mingxun
 * @create: 2024-10-13 17:46
 **/
@Data
public class CategoryForm {
    private Integer categoryId;
    //    类目名字
    private String categoryName;
    //    类目编号
    private Integer categoryType;
}
