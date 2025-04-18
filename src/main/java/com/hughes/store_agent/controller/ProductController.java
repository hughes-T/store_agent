package com.hughes.store_agent.controller;

import com.google.common.base.Preconditions;
import com.hughes.core.aop.TokenValidate;
import com.hughes.core.exceptions.DefaultExceptionHandler;
import com.hughes.core.model.ApiResponse;
import com.hughes.core.utils.ObjectUtil;
import com.hughes.store_agent.biz.IProductBiz;
import com.hughes.store_agent.pojo.dto.ProductDTO;
import com.hughes.store_agent.pojo.query.ProductQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hts
 * @date 2025/4/18 11:23
 */
@Slf4j
@RequestMapping("/com/hughes/product")
@RestController
public class ProductController implements DefaultExceptionHandler {

    @Resource
    private IProductBiz productBiz;

    /**
     * 提报商品
     */
    @TokenValidate
    @PostMapping("/save")
    public ApiResponse save(@RequestBody ProductDTO dto) {
        productBiz.save(dto);
        return ApiResponse.success();
    }

    /**
     * 审核商品
     */
    @TokenValidate
    @PostMapping("/audit")
    public ApiResponse audit(@RequestBody ProductDTO dto) {
        productBiz.audit(dto);
        return ApiResponse.success();
    }

    /**
     * 商品详情
     */
    @PostMapping("/detail")
    public ApiResponse detail(@RequestBody ProductQuery query) {
        return ApiResponse.success(productBiz.detail(query));
    }

    /**
     * 商品列表查询
     */
    @PostMapping("/queryList")
    public ApiResponse queryList(@RequestBody ProductQuery query) {
        return ApiResponse.success(productBiz.queryList(query));
    }

    /**
     * 保存商品分类
     */
    @PostMapping("/savePdType")
    public ApiResponse savePdType() {
        return ApiResponse.success();
    }


    /**
     * 商品分类查询
     */
    @PostMapping("/queryPdTypeList")
    public ApiResponse queryPdTypeList() {
        return ApiResponse.success();
    }


}
