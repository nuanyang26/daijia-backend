package top.nuanyang26.daijia.rules.service;

import top.nuanyang26.daijia.model.form.rules.FeeRuleRequestForm;
import top.nuanyang26.daijia.model.vo.rules.FeeRuleResponseVo;

public interface FeeRuleService {

    //计算订单费用
    FeeRuleResponseVo calculateOrderFee(FeeRuleRequestForm calculateOrderFeeForm);
}
