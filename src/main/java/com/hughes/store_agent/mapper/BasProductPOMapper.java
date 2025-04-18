package com.hughes.store_agent.mapper;

import com.hughes.store_agent.pojo.po.BasProductPO;
import com.hughes.store_agent.pojo.query.ProductQuery;
import com.hughes.store_agent.pojo.vo.ProductVo;

import java.util.List;

/**
 * @author pn
 * @description 针对表【bas_product】的数据库操作Mapper
 * @createDate 2025-04-18 16:03:25
 * @Entity com.hughes.store_agent.pojo.po.BasProductPO
 */
public interface BasProductPOMapper {

    int deleteByPrimaryKey(Long id);

    int insert(BasProductPO record);

    int insertSelective(BasProductPO record);

    BasProductPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BasProductPO record);

    int updateByPrimaryKey(BasProductPO record);

    void updateFlow(BasProductPO req);

    int queryCount(ProductQuery query);

    List<ProductVo> queryList(ProductQuery query);

}
