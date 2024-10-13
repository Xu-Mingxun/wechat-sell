package com.mingxun.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mingxun.enums.ProductStatusEnum;
import com.mingxun.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "product_info")
@DynamicUpdate
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1808023597601707381L;

    @Id
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

    /* 状态, 0正常1下架*/
    private Integer productStatus;

    /* 类目编号*/
    private Integer categoryType;

    /* 创建时间*/
    private Date createTime;

    /* 修改时间*/
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
