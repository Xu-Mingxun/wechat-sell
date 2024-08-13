package com.mingxun.dataobject.mapper;

import com.mingxun.dataobject.ProductCategory;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.*;

public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name, category_type) values (#{category_name, jdbcType=VARCHAR}, #{category_type, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    // @Insert("insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    // int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType}")
    // 将数据库中的列，映射到返回值的属性
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    // 使用xml文件来编写mybatis

    int insertByObject(ProductCategory productCategory);

    int deleteByCategoryType(Integer categoryType);

    ProductCategory selectByCategoryType(Integer categoryType);

    List<ProductCategory> selectByCategoryName(String categoryName);

    int updateByCategoryType(@Param("categoryName") String categoryName, @Param("categoryType") Integer categoryType);

    // update By ProductCategory through categoryId
    int updateByObject(ProductCategory productCategory);
}
