<html>
<#include "../common/header.ftl">

<body>

<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>名称</th>
<#--                            <th>图片</th>-->
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list productDTOPage.content as productDTO>
                            <tr>
                                <td>${productDTO.productId}</td>
                                <td>${productDTO.productName}</td>
<#--                                <td>${productDTO.productIcon}</td>-->
                                <td>${productDTO.productPrice}</td>
                                <td>${productDTO.productStock}</td>
                                <td>${productDTO.productDescription}</td>
                                <td>${productDTO.categoryType}</td>
                                <td>${productDTO.createTime}</td>
                                <td>${productDTO.updateTime}</td>
                                <td><a href="/wechat-sell/seller/product/index?productId=${productDTO.productId}">修改</a></td>
                                <td>
                                    <#if productDTO.getProductStatusEnum().message == "在架">
                                        <a href="/wechat-sell/seller/product/off_sale?productId=${productDTO.productId}">下架</a>
                                    <#else>
                                        <a href="/wechat-sell/seller/product/on_sale?productId=${productDTO.productId}">上架</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/wechat-sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                        </#if>

                        <#list 1..productDTOPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/wechat-sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>

                        <#if currentPage gte productDTOPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/wechat-sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>