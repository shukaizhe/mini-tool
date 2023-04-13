package com.hfut.beike.component.cmp;

import com.hfut.beike.common.enums.PriceTypeEnum;
import com.hfut.beike.component.cmp.constant.CmpConstant;
import com.hfut.beike.component.slot.PriceContext;
import com.hfut.beike.entity.vo.PriceStepVO;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * 优惠券抵扣计算组件
 */
@Slf4j
@LiteflowComponent(value = CmpConstant.COUPON_CMP, id = CmpConstant.COUPON_CMP, name = "优惠券抵扣计算组件")
public class CouponCmp extends CustomNodeComponent<PriceContext> {
    @Override
    public void process() throws Exception {
        PriceContext context = getContext();

        /**这里Mock下根据couponId取到的优惠卷面值为15元**/
        Long couponId = context.getCouponId();
        BigDecimal couponPrice = new BigDecimal(15);

        BigDecimal prePrice = context.getLastestPriceStep().getCurrPrice();
        BigDecimal currPrice = prePrice.subtract(couponPrice);

        context.addPriceStep(new PriceStepVO(PriceTypeEnum.COUPON_DISCOUNT,
                couponId.toString(),
                prePrice,
                currPrice.subtract(prePrice),
                currPrice,
                PriceTypeEnum.COUPON_DISCOUNT.getName()));
    }

    @Override
    public boolean isAccess() {
        PriceContext context = this.getContextBean(PriceContext.class);
        return context.getCouponId() != null;
    }
}
