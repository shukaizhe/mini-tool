package com.hfut.beike.component.cmp;

import com.hfut.beike.component.cmp.constant.CmpConstant;
import com.hfut.beike.component.slot.PriceContext;
import com.hfut.beike.entity.vo.PriceStepVO;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;

/**
 * 订单最终价格计算器
 */

@Slf4j
@LiteflowComponent(value = CmpConstant.PRICE_RESULT_CMP, id = CmpConstant.PRICE_RESULT_CMP, name = "订单最终价格计算器")
public class PriceResultCmp extends CustomNodeComponent<PriceContext> {
    @Override
    public void process() throws Exception {
        //算出订单最后的价格，因为priceChange有正负，所以这里统一加起来
        PriceContext context = getContext();
        BigDecimal finalPrice = new BigDecimal(0);
        for(PriceStepVO step : context.getPriceStepList()){
            finalPrice = finalPrice.add(step.getPriceChange());
        }
        context.setFinalOrderPrice(finalPrice);
    }

    @Override
    public boolean isAccess() {
        PriceContext context = this.getContextBean(PriceContext.class);
        return CollectionUtils.isNotEmpty(context.getPriceStepList());
    }
}
