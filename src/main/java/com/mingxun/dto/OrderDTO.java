package com.mingxun.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mingxun.dataobject.OrderDetail;
import com.mingxun.enums.OrderStatusEnum;
import com.mingxun.enums.PayStatusEnum;
import com.mingxun.utils.serializer.Date2LongSerializer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Id;
import lombok.Data;

@Data
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    /* 订单id*/
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
    private Integer orderStatus;

    /* 支付状态，默认为0未支付*/
    private Integer payStatus;

    /* 创建时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /* 更新时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;
}
