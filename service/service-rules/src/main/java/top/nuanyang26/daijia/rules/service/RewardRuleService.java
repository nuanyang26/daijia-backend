package top.nuanyang26.daijia.rules.service;

import top.nuanyang26.daijia.model.form.rules.RewardRuleRequestForm;
import top.nuanyang26.daijia.model.vo.rules.RewardRuleResponseVo;

public interface RewardRuleService {

    RewardRuleResponseVo calculateOrderRewardFee(RewardRuleRequestForm rewardRuleRequestForm);
}
