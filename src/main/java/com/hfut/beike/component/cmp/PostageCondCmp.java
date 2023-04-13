package com.hfut.beike.component.cmp;

import com.hfut.beike.component.cmp.constant.CmpConstant;
import com.hfut.beike.component.slot.PriceContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * 运费条件组件
 */
@Slf4j
@LiteflowComponent(value = CmpConstant.POSTAGE_COND_CMP, id = CmpConstant.POSTAGE_COND_CMP, name = "运费条件组件")
public class PostageCondCmp extends NodeSwitchComponent {
    @Override
    public String processSwitch() {
        PriceContext context = this.getContextBean(PriceContext.class);
        //根据参数oversea来判断是否境外购，转到相应的组件
        boolean oversea = context.isOversea();
        if(oversea){
            return "overseaPostageCmp";
        }else{
            return "postageCmp";
        }
    }
}
