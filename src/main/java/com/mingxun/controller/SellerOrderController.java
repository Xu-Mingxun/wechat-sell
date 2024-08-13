package com.mingxun.controller;

import com.mingxun.VO.ResultVO;
import com.mingxun.dto.OrderDTO;
import com.mingxun.service.OrderService;
import com.mingxun.utils.ResultVOResult;
import java.util.Map;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/* 卖家端订单*/
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;
    /**
    * @Description:
    * @Param: [page] 第几页，从第1页开始
    * @Param: [size] 1页有多少数据
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: xu-mingxun
    * @Date: 2024/8/8
    */
    @GetMapping("/list")
    @ResponseBody
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage", orderDTOPage);
        return new ModelAndView("order/list", map);
    }
}
