package com.mingxun.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/*商品（包含类目）*/
@Data
public class ProductVO {

    //此注解作用, 返回给前端的是name
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categorytType;

    private List<ProductInfoVO>  productInfoVOList;
}
