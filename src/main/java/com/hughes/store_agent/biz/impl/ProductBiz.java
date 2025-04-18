package com.hughes.store_agent.biz.impl;

import com.google.common.base.Preconditions;
import com.hughes.core.consts.FlowStatus;
import com.hughes.core.consts.GlobalConstants;
import com.hughes.core.model.GridPage;
import com.hughes.core.utils.ObjectUtil;
import com.hughes.store_agent.biz.IProductBiz;
import com.hughes.store_agent.mapper.BasProductPOMapper;
import com.hughes.store_agent.pojo.dto.ProductDTO;
import com.hughes.store_agent.pojo.po.BasProductPO;
import com.hughes.store_agent.pojo.query.ProductQuery;
import com.hughes.store_agent.pojo.vo.ProductVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hts
 * @date 2025/4/18 15:04
 */

@Service
public class ProductBiz implements IProductBiz {

    @Resource
    private BasProductPOMapper productPOMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void save(ProductDTO dto) {
        BasProductPO po = ObjectUtil.beanConvert(dto, BasProductPO.class);
        boolean isAdd = ObjectUtil.isEmpty(dto.getId());
        if (isAdd) {
            po.init();
            productPOMapper.insert(po);
        } else {
            //审批状态判定 不支持 9->3
            BasProductPO oriPo = productPOMapper.selectByPrimaryKey(dto.getId());
            Preconditions.checkArgument(GlobalConstants.FLOW_STATUS_IN_AUDIT.equals(oriPo.getFlowStatus()), "该商品审批状态不允许操作");
            po.mod();
            productPOMapper.updateByPrimaryKey(po);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void audit(ProductDTO dto) {
        Preconditions.checkArgument(ObjectUtil.isNotEmpty(dto.getId()), "商品ID不能为空");
        Preconditions.checkArgument(ObjectUtil.isNotEmpty(dto.getFlowStatus()), "审批状态不能为空");
        BasProductPO oriPo = productPOMapper.selectByPrimaryKey(dto.getId());
        // 审批状态判定 仅支持 3->1 或 3->2
        Preconditions.checkArgument(GlobalConstants.FLOW_STATUS_IN_AUDIT.equals(oriPo.getFlowStatus()), "该商品审批状态不允许操作");
        BasProductPO auditParam = new BasProductPO();
        auditParam.setId(dto.getId());
        auditParam.mod();
        auditParam.setFlowStatus(dto.getFlowStatus());
        auditParam.setFlowRemark(dto.getFlowRemark());
        productPOMapper.updateFlow(auditParam);
    }

    @Override
    public ProductVo detail(ProductQuery query) {
        Preconditions.checkArgument(ObjectUtil.isNotEmpty(query.getId()), "商品ID不能为空");
        return productPOMapper.queryList(query).get(0);
    }

    @Override
    public GridPage<ProductVo, Object> queryList(ProductQuery query) {
        GridPage<ProductVo, Object> gridPage = GridPage.ofTotal(productPOMapper.queryCount(query));
        if (gridPage.ifCounts()) {
            query.setPageParam();
            List<ProductVo> rows = productPOMapper.queryList(query);
            gridPage.setRows(rows);
        }
        return gridPage;
    }


}
