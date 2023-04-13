package com.hfut.beike.component.cmp;

import com.hfut.beike.common.enums.PriceTypeEnum;
import com.hfut.beike.component.cmp.constant.CmpConstant;
import com.hfut.beike.component.slot.PriceContext;
import com.hfut.beike.entity.vo.PriceStepVO;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 会员折扣计算组件
 */
@Slf4j
@LiteflowComponent(value = CmpConstant.MEMBER_DISCOUNT_CMP, id = CmpConstant.MEMBER_DISCOUNT_CMP, name = "会员折扣计算组件")
public class MemberDiscountCmp extends CustomNodeComponent<PriceContext> {
    @Override
    public void process() throws Exception {
        PriceContext context = getContext();
        String memberCode = context.getMemberCode();

        // 这里Mock下通过memberCode去查会员等级表然后获取的会员折扣为9折的代码
        BigDecimal memberDiscount = new BigDecimal("0.9");

        //进行计算会员折扣
        BigDecimal prePrice = context.getLastestPriceStep().getCurrPrice();
        BigDecimal currPrice = prePrice.multiply(memberDiscount).setScale(2, RoundingMode.HALF_UP);

        //加入到价格步骤中
        context.addPriceStep(new PriceStepVO(PriceTypeEnum.MEMBER_DISCOUNT,
                memberCode,
                prePrice,
                currPrice.subtract(prePrice),
                currPrice,
                PriceTypeEnum.MEMBER_DISCOUNT.getName()));

    }

    @Override
    public boolean isAccess() {
        PriceContext context = this.getContextBean(PriceContext.class);
        return CollectionUtils.isNotEmpty(context.getProductPackList())
                && StringUtils.isNotBlank(context.getMemberCode());
    }
}
