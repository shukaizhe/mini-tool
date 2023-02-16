package com.hfut.beike.controller;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.hfut.beike.common.R;
import com.hfut.beike.common.enums.CategoryEnum;
import com.hfut.beike.common.enums.OrderChannelEnum;
import com.hfut.beike.common.enums.PromotionTypeEnum;
import com.hfut.beike.common.enums.SkuSourceEnum;
import com.hfut.beike.component.slot.PriceContext;
import com.hfut.beike.entity.vo.PriceCalcReqVO;
import com.hfut.beike.entity.vo.ProductPackVO;
import com.hfut.beike.entity.vo.PromotionInfoVO;
import com.hfut.beike.expection.ApiErrorCode;
import com.hfut.beike.expection.IErrorCode;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname TableController
 * @Description
 * @Date 2023/1/29 16:06
 * @Created by shukz
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("table")
public class TableController extends ApiController {

    @Resource
    private FlowExecutor flowExecutor;

    @GetMapping(value = "/getJson")
    public R<?> getJson(){
        JSONObject jsonObject = new JSONObject(true);
        PriceCalcReqVO req = mockReq();
        String text = JSON.toJSONString(req);
        jsonObject = JSON.parseObject(text, Feature.OrderedField);
        return success(jsonObject);
    }

    @PostMapping("/submit")
    @ResponseBody
    public R<?> submit(@Nullable @RequestBody String reqData){
        try{
            PriceCalcReqVO req = JSON.parseObject(reqData,PriceCalcReqVO.class);
            Class<?>[] classes = new Class[]{PriceContext.class};
            LiteflowResponse response = flowExecutor.execute2Resp("mainChain", req, classes);
            return success(response.getContextBean(PriceContext.class).getPrintLog());
        }catch (Exception e){
            e.printStackTrace();
            return failed(ApiErrorCode.FAILED);
        }

    }

    private PriceCalcReqVO mockReq(){
        PriceCalcReqVO req = new PriceCalcReqVO();
        req.setOrderNo("SO2020070611120001");
        req.setOversea(false);
        req.setMemberCode("M21152");
        req.setOrderChannel(OrderChannelEnum.APP);
        req.setCouponId(80081L);
        List<ProductPackVO> productPackList = new ArrayList<>();
        req.setProductPackList(productPackList);

        ProductPackVO productPack = new ProductPackVO();
        productPack.setProductId(5001L);
        productPack.setProductCode("PD5001XC");
        productPack.setSkuId(67001441L);
        productPack.setSkuCode("SKU5001XC001");
        productPack.setSkuName("夏季运动女式短裙M");
        productPack.setSkuSource(SkuSourceEnum.RAW);
        productPack.setCategory(CategoryEnum.CLOTHES);
        productPack.setSalePrice(new BigDecimal("139.00"));
        productPack.setCount(2);
        productPack.setPromotionList(ListUtil.toList(
                new PromotionInfoVO(1001L,"PM1001","夏季满减活动", PromotionTypeEnum.FULL_CUT),
                new PromotionInfoVO(1002L,"PM1002","夏季满折活动", PromotionTypeEnum.FULL_DISCOUNT)));
        productPackList.add(productPack);

        productPack = new ProductPackVO();
        productPack.setProductId(6001L);
        productPack.setProductCode("PD6001XC");
        productPack.setSkuId(67002334L);
        productPack.setSkuCode("SKU6001XC001");
        productPack.setSkuName("男士迷彩短袜均码");
        productPack.setSkuSource(SkuSourceEnum.RAW);
        productPack.setCategory(CategoryEnum.CLOTHES);
        productPack.setSalePrice(new BigDecimal("59.00"));
        productPack.setCount(3);
        productPack.setPromotionList(ListUtil.toList(
                new PromotionInfoVO(1001L,"PM1001","夏季满减活动", PromotionTypeEnum.FULL_CUT)));
        productPackList.add(productPack);

        productPack = new ProductPackVO();
        productPack.setProductId(8001L);
        productPack.setProductCode("PD8001XC");
        productPack.setSkuId(87002001L);
        productPack.setSkuCode("SKU8001XC001");
        productPack.setSkuName("纯棉毛巾");
        productPack.setSkuSource(SkuSourceEnum.RAW);
        productPack.setCategory(CategoryEnum.DAILY_USE);
        productPack.setSalePrice(new BigDecimal("28.00"));
        productPack.setCount(5);
        productPack.setPromotionList(ListUtil.toList(
                new PromotionInfoVO(1002L,"PM1002","夏季满折活动", PromotionTypeEnum.FULL_DISCOUNT)));
        productPackList.add(productPack);

        productPack = new ProductPackVO();
        productPack.setProductId(9001L);
        productPack.setProductCode("PD9001XC");
        productPack.setSkuId(97552001L);
        productPack.setSkuCode("SKU9001XC001");
        productPack.setSkuName("杀菌护手凝胶");
        productPack.setSkuSource(SkuSourceEnum.RAW);
        productPack.setCategory(CategoryEnum.DAILY_USE);
        productPack.setSalePrice(new BigDecimal("30"));
        productPack.setCount(2);
        productPack.setPromotionList(ListUtil.toList(
                new PromotionInfoVO(1003L,"PM1003","618抢购活动", PromotionTypeEnum.RUSH_BUY)));
        productPackList.add(productPack);

        return req;
    }

}
