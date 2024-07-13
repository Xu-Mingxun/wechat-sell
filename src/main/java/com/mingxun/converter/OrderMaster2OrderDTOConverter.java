package com.mingxun.converter;

import com.mingxun.dataobject.OrderMaster;
import com.mingxun.dto.OrderDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;

public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(
                OrderMaster2OrderDTOConverter::convert
        ).collect(Collectors.toList());
    }
}
