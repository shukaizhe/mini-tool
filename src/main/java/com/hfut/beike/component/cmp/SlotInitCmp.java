package com.hfut.beike.component.cmp;

import com.hfut.beike.component.cmp.constant.CmpConstant;
import com.hfut.beike.component.slot.PriceContext;
import com.hfut.beike.entity.vo.PriceCalcReqVO;
import com.yomahub.liteflow.annotation.LiteflowComponent;


/**
 * Slot初始化组件
 */
@LiteflowComponent(value = CmpConstant.SLOT_INIT_CMP, id = CmpConstant.SLOT_INIT_CMP, name = "Slot初始化组件")
public class SlotInitCmp extends CustomNodeComponent<PriceContext> {
    @Override
    public void process() throws Exception {
        //把主要参数冗余到slot里
        PriceCalcReqVO req = this.getRequestData();
        PriceContext context = getContext();
        context.setOrderNo(req.getOrderNo());
        context.setOversea(req.isOversea());
        context.setMemberCode(req.getMemberCode());
        context.setOrderChannel(req.getOrderChannel());
        context.setProductPackList(req.getProductPackList());
        context.setCouponId(req.getCouponId());
    }

    @Override
    public boolean isAccess() {
        PriceCalcReqVO req = this.getSlot().getRequestData();
        return req != null;
    }
}
