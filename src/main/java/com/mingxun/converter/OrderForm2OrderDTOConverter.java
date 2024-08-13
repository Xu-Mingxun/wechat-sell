package com.mingxun.converter;

import com.google.gson.reflect.TypeToken;
import com.mingxun.dataobject.OrderDetail;
import com.mingxun.dto.OrderDTO;
import com.mingxun.enums.ResultEnum;
import com.mingxun.exception.SellException;
import com.mingxun.form.OrderForm;
import java.util.ArrayList;
import java.util.List;
import org.aspectj.weaver.ast.Or;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderForm2OrderDTOConverter {
    private static final Logger log = LoggerFactory.getLogger(OrderForm2OrderDTOConverter.class);

    public static OrderDTO converter(OrderForm orderForm) {
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        // gson.fromJson(orderForm.getItems(),
        //         new TypeToken<List<OrderDetail>>(){}.getType());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误，string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
