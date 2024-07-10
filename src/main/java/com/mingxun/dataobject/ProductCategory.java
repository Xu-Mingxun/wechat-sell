package com.mingxun.dataobject;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.Date;
import lombok.Data;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.DynamicUpdate;

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
public class ProductCategory {
    // 命名方式都是将下划线改为大写
//    类目id
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer categoryId;
//    类目名字
    private String categoryName;
//    类目编号
    private Integer categoryType;

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
