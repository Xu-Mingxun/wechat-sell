package com.mingxun.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

// 类目
/*  数据库表名为product_category
    如果表名为s_product_category，并且类名不匹配
    需要加@Table(name = "s_product_category")
* */
@Entity
@Table(name = "product_category")
@DynamicUpdate
// lombok包，Data生成getter，setter，Tostring
@Data
@DynamicInsert
public class ProductCategory {
    // 命名方式都是将下划线改为大写
//    类目id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer categoryId;
    //    类目名字
    private String categoryName;
    //    类目编号
    private Integer categoryType;

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public ProductCategory() {

    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType=" + categoryType +
                '}';
    }
}
