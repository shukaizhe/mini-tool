package com.hfut.beike.component.cmp;

import com.hfut.beike.component.slot.PriceContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;

/**
 * 运费条件组件
 */
@LiteflowComponent(value = "postageCondCmp", id = "postageCondCmp", name = "运费条件组件")
public class PostageCondCmp extends NodeSwitchComponent {
    @Override
    public String processSwitch() throws Exception {
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
