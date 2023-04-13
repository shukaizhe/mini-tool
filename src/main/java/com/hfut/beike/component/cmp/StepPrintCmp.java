package com.hfut.beike.component.cmp;

import com.hfut.beike.component.cmp.constant.CmpConstant;
import com.hfut.beike.component.slot.PriceContext;
import com.hfut.beike.entity.vo.PriceStepVO;
import com.hfut.beike.entity.vo.ProductPackVO;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.math.RoundingMode;
import java.text.MessageFormat;

/**
 * 步骤日志生成组件
 */
@Slf4j
@LiteflowComponent(value = CmpConstant.STEP_PRINT_CMP, id = CmpConstant.STEP_PRINT_CMP, name = "步骤日志生成组件")
public class StepPrintCmp extends CustomNodeComponent<PriceContext> {
    @Override
    public void process() throws Exception {
        PriceContext context = getContext();
        StringBuilder logStr = new StringBuilder();

        logStr.append(MessageFormat.format("订单号[{0}]的价格计算的明细结果:\n", context.getOrderNo()));
        logStr.append("|===================================================================\n");
        for(ProductPackVO pack : context.getProductPackList()){
            logStr.append(MessageFormat.format("|   {0} [{1}] [{2}]   {3} X {4}\n",
                    pack.getSkuName(),
                    pack.getProductCode(),
                    pack.getSkuCode(),
                    pack.getSalePrice().setScale(2, RoundingMode.HALF_UP).toString(),
                    pack.getCount()));
        }

        logStr.append("|===================================================================\n");
        for(PriceStepVO step : context.getPriceStepList()){
            logStr.append(MessageFormat.format("|   [{0} : {1}]\n",step.getStepDesc(),step.getPriceChange().setScale(2, RoundingMode.HALF_UP).toString()));
        }
        logStr.append(MessageFormat.format("|   [最终价 : {0}]\n",context.getFinalOrderPrice().setScale(2, RoundingMode.HALF_UP).toString()));
        logStr.append("|===================================================================\n");
        log.info(logStr.toString());
        context.setPrintLog(logStr.toString());
    }

    @Override
    public boolean isAccess() {
        PriceContext context = getContext();
        return CollectionUtils.isNotEmpty(context.getPriceStepList());
    }

}
