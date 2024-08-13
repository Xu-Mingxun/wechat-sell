package com.mingxun.controller;

import com.mingxun.VO.ResultVO;
import com.mingxun.converter.OrderForm2OrderDTOConverter;
import com.mingxun.dto.OrderDTO;
import com.mingxun.enums.ResultEnum;
import com.mingxun.exception.SellException;
import com.mingxun.form.OrderForm;
import com.mingxun.service.BuyerService;
import com.mingxun.service.Impl.OrderServiceImpl;
import com.mingxun.service.OrderService;
import com.mingxun.utils.ResultVOResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    // 创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOResult.success(map);
    }

//     订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);

        return ResultVOResult.success(orderDTOPage.getContent());
        // return ResultVOResult.success();
    }


//     订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOResult.success(orderDTO);
    }


//     取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.cancelOrder(openid, orderId);
        orderService.cancel(orderDTO);
        return ResultVOResult.success();
    }

}
