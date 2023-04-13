package com.hfut.beike.component.cmp;

import com.hfut.beike.common.enums.PriceTypeEnum;
import com.hfut.beike.component.cmp.constant.CmpConstant;
import com.hfut.beike.component.slot.PriceContext;
import com.hfut.beike.entity.vo.PriceStepVO;
import com.hfut.beike.entity.vo.ProductPackVO;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;


import java.math.BigDecimal;
import java.util.List;

/**
 * 价格步骤初始化器(把原价初始化进去)
 */
@Slf4j
@LiteflowComponent(value = CmpConstant.PRICE_STEP_INIT_CMP, id = CmpConstant.PRICE_STEP_INIT_CMP, name = "价格步骤初始化器(把原价初始化进去)")
public class PriceStepInitCmp extends CustomNodeComponent<PriceContext> {
    @Override
    public void process() throws Exception {
        PriceContext context = getContext();

        //初始化价格步骤
        List<ProductPackVO> packList = context.getProductPackList();
        BigDecimal totalOriginalPrice = new BigDecimal(0);
        for(ProductPackVO packItem : packList){
            totalOriginalPrice = totalOriginalPrice.add(packItem.getSalePrice().multiply(new BigDecimal(packItem.getCount())));
        }
        context.addPriceStep(new PriceStepVO(PriceTypeEnum.ORIGINAL,
                null,
                null,
                totalOriginalPrice,
                totalOriginalPrice,
                PriceTypeEnum.ORIGINAL.getName()));
        context.setOriginalOrderPrice(totalOriginalPrice);
    }

    @Override
    public boolean isAccess() {
        PriceContext context = this.getContextBean(PriceContext.class);
        return CollectionUtils.isNotEmpty(context.getProductPackList());
    }
}
