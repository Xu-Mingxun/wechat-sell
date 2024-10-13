package com.mingxun.service.Impl;

import com.mingxun.dataobject.ProductInfo;
import com.mingxun.dto.CartDTO;
import com.mingxun.enums.ProductStatusEnum;
import com.mingxun.enums.ResultEnum;
import com.mingxun.exception.SellException;
import com.mingxun.repository.ProductInfoRepository;
import com.mingxun.service.ProductService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    // @CachePut(cacheNames = "product", key = "123")
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    // @Cacheable(cacheNames = "product", key = "123")
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).get();
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            if (!repository.findById(cartDTO.getProductId()).isPresent()) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            if (!repository.findById(cartDTO.getProductId()).isPresent()) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = repository.findById(productId).get();
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw  new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        // 更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = repository.findById(productId).get();
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        // 更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }
}
