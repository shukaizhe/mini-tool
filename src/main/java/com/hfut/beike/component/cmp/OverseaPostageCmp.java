package com.hfut.beike.component.cmp;

import com.hfut.beike.common.enums.PriceTypeEnum;
import com.hfut.beike.component.cmp.constant.CmpConstant;
import com.hfut.beike.component.slot.PriceContext;
import com.hfut.beike.entity.vo.PriceStepVO;
import com.yomahub.liteflow.annotation.LiteflowComponent;

import java.math.BigDecimal;

/**
 * 境外购运费计算组件
 */
@LiteflowComponent(value = CmpConstant.OVERSEA_POSTAGE_CMP, id = CmpConstant.OVERSEA_POSTAGE_CMP, name = "境外购运费计算组件")
public class OverseaPostageCmp extends CustomNodeComponent<PriceContext> {
    @Override
    public void process() throws Exception {
        PriceContext context = getContext();

        /*这里Mock境外购运费的策略是：不管多少钱，都要加上15元运费*/
        BigDecimal postage = new BigDecimal(15);
        BigDecimal prePrice = context.getLastestPriceStep().getCurrPrice();
        BigDecimal currPrice = prePrice.add(postage);

        context.addPriceStep(new PriceStepVO(PriceTypeEnum.OVERSEAS_POSTAGE,
                null,
                prePrice,
                currPrice.subtract(prePrice),
                currPrice,
                PriceTypeEnum.OVERSEAS_POSTAGE.getName()));
    }
}
