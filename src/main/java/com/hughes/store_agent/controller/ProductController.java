package com.hughes.store_agent.controller;

import com.hughes.core.exceptions.DefaultExceptionHandler;
import com.hughes.core.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hts
 * @date 2025/4/18 11:23
 */
@Slf4j
@RequestMapping("/com/hughes/product")
@RestController
public class ProductController implements DefaultExceptionHandler {


    /**
     * 提报商品
     */
    public ApiResponse saveProduct(){

        return ApiResponse.success();
    }


}
