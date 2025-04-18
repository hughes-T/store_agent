package com.hughes.store_agent.biz;

import com.hughes.core.model.GridPage;
import com.hughes.store_agent.pojo.dto.ProductDTO;
import com.hughes.store_agent.pojo.query.ProductQuery;
import com.hughes.store_agent.pojo.vo.ProductVo;

/**
 * 商品管理
 *
 * @author hts
 * @date 2025/4/18 11:00
 */
public interface IProductBiz {


    void save(ProductDTO dto);


    void audit(ProductDTO dto);

    ProductVo detail(ProductQuery query);

    GridPage<ProductVo, Object> queryList(ProductQuery query);
}
