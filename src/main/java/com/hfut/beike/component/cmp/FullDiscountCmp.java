package com.hfut.beike.component.cmp;

import com.hfut.beike.common.enums.PriceTypeEnum;
import com.hfut.beike.common.enums.PromotionTypeEnum;
import com.hfut.beike.component.cmp.constant.CmpConstant;
import com.hfut.beike.component.slot.PriceContext;
import com.hfut.beike.entity.vo.PriceStepVO;
import com.hfut.beike.entity.vo.ProductPackVO;
import com.hfut.beike.entity.vo.PromotionPackVO;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 满折计算组件
 */
@Slf4j
@LiteflowComponent(value = CmpConstant.FULL_DISCOUNT_CMP, id = CmpConstant.FULL_DISCOUNT_CMP, name = "满折计算组件")
public class FullDiscountCmp extends CustomNodeComponent<PriceContext> {
    @Override
    public void process() throws Exception {
        PriceContext context = getContext();
        PromotionPackVO promotionPack = getMatchPromotion();

        /*这里Mock下根据优惠信息查到的满折信息为：满200，打9折*/
        BigDecimal triggerPrice = new BigDecimal(200);
        BigDecimal discountRate = new BigDecimal("0.9");

        //从PromotionPack对象中取到这个优惠关联的商品信息，判断是否超过了触发满折的金额
        BigDecimal reletedProductTotalPrice = new BigDecimal(0);
        if (Optional.ofNullable(promotionPack).isPresent()) {
            for (ProductPackVO productPack : promotionPack.getRelatedProductPackList()) {
                reletedProductTotalPrice = reletedProductTotalPrice.add(productPack.getSalePrice().multiply(new BigDecimal(productPack.getCount())));
            }
            if (reletedProductTotalPrice.compareTo(triggerPrice) >= 0) {
                BigDecimal prePrice = context.getLastestPriceStep().getCurrPrice();
                BigDecimal currPrice = prePrice.multiply(discountRate).setScale(2, RoundingMode.HALF_UP);

                context.addPriceStep(new PriceStepVO(PriceTypeEnum.PROMOTION_DISCOUNT,
                        promotionPack.getId().toString(),
                        prePrice,
                        currPrice.subtract(prePrice),
                        currPrice,
                        PriceTypeEnum.PROMOTION_DISCOUNT.getName() + "[满折]"));
            }

        }

    }

    @Override
    public boolean isAccess() {
        //过滤出优惠信息列表中有没有满折这个活动，如果有，则进入这个组件，反义就不进入
        PromotionPackVO promotionPack = getMatchPromotion();
        return promotionPack != null;
    }

    private PromotionPackVO getMatchPromotion() {
        PriceContext context = getContext();

        List<PromotionPackVO> matchList = context.getPromotionPackList().stream().filter(promotionPackVO -> promotionPackVO.getPromotionType().equals(PromotionTypeEnum.FULL_DISCOUNT)).collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(matchList)) {
            return matchList.get(0);
        } else {
            return null;
        }
    }
}
