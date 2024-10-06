package top.nuanyang26.daijia.rules.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.nuanyang26.daijia.common.result.Result;
import top.nuanyang26.daijia.model.form.rules.FeeRuleRequestForm;
import top.nuanyang26.daijia.model.vo.rules.FeeRuleResponseVo;

@FeignClient(value = "service-rules")
public interface FeeRuleFeignClient {

    /**
     * 计算订单费用
     * @param calculateOrderFeeForm
     * @return
     */
    @PostMapping("/rules/fee/calculateOrderFee")
    Result<FeeRuleResponseVo> calculateOrderFee(@RequestBody FeeRuleRequestForm calculateOrderFeeForm);
}