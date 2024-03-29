package com.hfut.beike.component.cmp;

import com.hfut.beike.common.enums.PriceTypeEnum;
import com.hfut.beike.common.enums.PromotionTypeEnum;
import com.hfut.beike.component.cmp.constant.CmpConstant;
import com.hfut.beike.component.slot.PriceContext;
import com.hfut.beike.entity.vo.PriceStepVO;
import com.hfut.beike.entity.vo.ProductPackVO;
import com.hfut.beike.entity.vo.PromotionPackVO;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 满减计算组件
 */
@LiteflowComponent(value = CmpConstant.FULL_CUT_CMP, id = CmpConstant.FULL_CUT_CMP, name = "满减计算组件")
public class FullCutCmp extends CustomNodeComponent<PriceContext> {
    @Override
    public void process() throws Exception {
        PriceContext slot = getContext();
        PromotionPackVO promotionPack = getMatchPromotion();

        // 这里Mock下根据优惠信息查到的满减信息为：满100，减5块
        BigDecimal triggerPrice = new BigDecimal(100);
        BigDecimal cutPrice = new BigDecimal(5);

        //从PromotionPack对象中取到这个优惠关联的商品信息，判断是否超过了触发满减的金额
        BigDecimal reletedProductTotalPrice = new BigDecimal(0);
        assert promotionPack != null;
        for (ProductPackVO productPack : promotionPack.getRelatedProductPackList()) {
            reletedProductTotalPrice = reletedProductTotalPrice.add(productPack.getSalePrice().multiply(new BigDecimal(productPack.getCount())));
        }
        if (reletedProductTotalPrice.compareTo(triggerPrice) >= 0) {
            BigDecimal prePrice = slot.getLastestPriceStep().getCurrPrice();
            BigDecimal currPrice = prePrice.subtract(cutPrice);

            slot.addPriceStep(new PriceStepVO(PriceTypeEnum.PROMOTION_DISCOUNT,
                    promotionPack.getId().toString(),
                    prePrice,
                    currPrice.subtract(prePrice),
                    currPrice,
                    PriceTypeEnum.PROMOTION_DISCOUNT.getName() + "[满减]"));
        }

    }

    @Override
    public boolean isAccess() {
        //过滤出优惠信息列表中有没有满减这个活动，如果有，则进入这个组件，反义就不进入
        PromotionPackVO promotionPack = getMatchPromotion();
        return promotionPack != null;
    }

    private PromotionPackVO getMatchPromotion() {
        PriceContext context = this.getContextBean(PriceContext.class);

        List<PromotionPackVO> matchList = context.getPromotionPackList().stream().filter(promotionPackVO -> promotionPackVO.getPromotionType().equals(PromotionTypeEnum.FULL_CUT)).collect(Collectors.toList());

        if (CollectionUtils.isNotEmpty(matchList)) {
            return matchList.get(0);
        } else {
            return null;
        }
    }
}
