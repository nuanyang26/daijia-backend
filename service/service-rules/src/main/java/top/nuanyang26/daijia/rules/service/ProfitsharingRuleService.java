package top.nuanyang26.daijia.rules.service;

import top.nuanyang26.daijia.model.form.rules.ProfitsharingRuleRequestForm;
import top.nuanyang26.daijia.model.vo.rules.ProfitsharingRuleResponseVo;

public interface ProfitsharingRuleService {

    ProfitsharingRuleResponseVo calculateOrderProfitsharingFee(ProfitsharingRuleRequestForm profitsharingRuleRequestForm);
}
