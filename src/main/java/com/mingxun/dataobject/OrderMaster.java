package com.mingxun.dataobject;

import com.mingxun.enums.OrderStatusEnum;
import com.mingxun.enums.PayStatusEnum;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

/* 买家订单*/
@Entity
@Data
// 因为更新时间需要实时更新
@DynamicUpdate
public class OrderMaster {
    /* 订单id*/
    @Id
    private String orderId;

    /* 买家名字*/
    private String buyerName;

    /* 买家手机号*/
    private String buyerPhone;

    /* 买家地址*/
    private String buyerAddress;

    /* 买家微信OpenId*/
    private String buyerOpenid;

    /* 订单总金额*/
    private BigDecimal orderAmount;

    /* 订单状态，默认为新下单*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /* 支付状态，默认为0未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /* 创建时间*/
    private Date createTime;

    /* 更新时间*/
    private Date updateTime;
}
