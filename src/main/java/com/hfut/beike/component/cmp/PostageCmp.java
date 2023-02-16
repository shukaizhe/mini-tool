package com.hfut.beike.component.cmp;

import com.hfut.beike.common.enums.PriceTypeEnum;
import com.hfut.beike.component.slot.PriceContext;
import com.hfut.beike.entity.vo.PriceStepVO;
import com.yomahub.liteflow.annotation.LiteflowComponent;

import java.math.BigDecimal;

/**
 * 国内运费计算组件
 */
@LiteflowComponent(value = "postageCmp", id = "postageCmp", name = "国内运费计算组件")
public class PostageCmp extends CustomNodeComponent<PriceContext> {
    @Override
    public void process() throws Exception {
        PriceContext context = getContext();

        /**这里Mock运费的策略是：满99免运费，不满99需要10块钱运费**/
        BigDecimal triggerPrice = new BigDecimal(99);
        BigDecimal postage = new BigDecimal(10);
        //先把运费加上去
        BigDecimal prePrice = context.getLastestPriceStep().getCurrPrice();
        BigDecimal currPrice = prePrice.add(postage);

        context.addPriceStep(new PriceStepVO(PriceTypeEnum.POSTAGE,
                null,
                prePrice,
                currPrice.subtract(prePrice),
                currPrice,
                PriceTypeEnum.POSTAGE.getName()));

        //判断运费是否满99了，满了99就去掉运费
        if(prePrice.compareTo(triggerPrice) >= 0){
            prePrice = context.getLastestPriceStep().getCurrPrice();
            currPrice = currPrice.subtract(postage);

            context.addPriceStep(new PriceStepVO(PriceTypeEnum.POSTAGE_FREE,
                    null,
                    prePrice,
                    currPrice.subtract(prePrice),
                    currPrice,
                    PriceTypeEnum.POSTAGE_FREE.getName()));
        }
    }
}
