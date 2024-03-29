package com.hfut.beike.component.cmp;

import com.hfut.beike.component.cmp.constant.CmpConstant;
import com.hfut.beike.component.slot.PriceContext;
import com.hfut.beike.entity.vo.PriceCalcReqVO;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * 初始化参数检查组件
 */
@Slf4j
@LiteflowComponent(value = CmpConstant.CHECK_CMP, id = CmpConstant.CHECK_CMP, name = "初始化参数检查组件")
public class CheckCmp extends CustomNodeComponent<PriceContext> {
    @Override
    public void process() throws Exception {
        PriceContext context = getContext();
        //拿到请求参数
        PriceCalcReqVO req = this.getSlot().getRequestData();
        /*这里Mock参数验证过程*/
        log.info("参数验证完成");
    }
}
