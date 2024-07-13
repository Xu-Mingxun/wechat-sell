package com.mingxun.service;

import com.mingxun.dataobject.ProductInfo;
import com.mingxun.dto.CartDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductInfo findOne(String productId);

    /*
    * 查询所有在架商品列表
    * @return
    * */
    List<ProductInfo> findUpAll();

    // 可分页
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    // 加库存
    void increaseStock(List<CartDTO> cartDTOList);

    // 减库存
    void decreaseStock(List<CartDTO> cartDTOList);

}
