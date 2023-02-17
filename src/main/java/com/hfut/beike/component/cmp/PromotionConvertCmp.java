package com.hfut.beike.component.cmp;

import cn.hutool.core.collection.ListUtil;
import com.hfut.beike.component.cmp.constant.CmpConstant;
import com.hfut.beike.component.slot.PriceContext;
import com.hfut.beike.entity.vo.ProductPackVO;
import com.hfut.beike.entity.vo.PromotionInfoVO;
import com.hfut.beike.entity.vo.PromotionPackVO;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 把商品包的优惠信息转换成以优惠信息为主要维度的对象，以便于后面优惠信息的计算
 */
@LiteflowComponent(value = CmpConstant.PROMOTION_CONVERT_CMP, id = CmpConstant.PROMOTION_CONVERT_CMP, name = "把商品包的优惠信息转换成以优惠信息为主要维度的对象，以便于后面优惠信息的计算")
public class PromotionConvertCmp extends CustomNodeComponent<PriceContext> {
    @Override
    public void process() throws Exception {
        PriceContext context = getContext();
        List<PromotionPackVO> promotionPackList = new ArrayList<>();

        PromotionPackVO promotionPack = null;
        for(ProductPackVO pack : context.getProductPackList()){
            if(CollectionUtils.isEmpty(pack.getPromotionList())){
                continue;
            }
            for(PromotionInfoVO promotion : pack.getPromotionList()){
                promotionPack = new PromotionPackVO();
                promotionPack.setId(promotion.getId());
                if(promotionPackList.contains(promotionPack)){
                    promotionPack = promotionPackList.get(promotionPackList.indexOf(promotionPack));
                    if(promotionPack.getRelatedProductPackList().contains(pack)){
                        continue;
                    }else{
                        promotionPack.getRelatedProductPackList().add(pack);
                    }
                }else{
                    BeanUtils.copyProperties(promotion,promotionPack);
                    promotionPack.setRelatedProductPackList(ListUtil.toList(pack));
                    promotionPackList.add(promotionPack);
                }
            }
        }
        context.setPromotionPackList(promotionPackList);
    }

    @Override
    public boolean isAccess() {
        PriceContext context = this.getContextBean(PriceContext.class);
        return CollectionUtils.isNotEmpty(context.getProductPackList());

    }
}
