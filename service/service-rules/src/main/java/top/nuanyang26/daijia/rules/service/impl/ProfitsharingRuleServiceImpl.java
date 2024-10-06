package top.nuanyang26.daijia.rules.service.impl;

import top.nuanyang26.daijia.model.form.rules.ProfitsharingRuleRequest;
import top.nuanyang26.daijia.model.form.rules.ProfitsharingRuleRequestForm;
import top.nuanyang26.daijia.model.vo.rules.ProfitsharingRuleResponse;
import top.nuanyang26.daijia.model.vo.rules.ProfitsharingRuleResponseVo;
import top.nuanyang26.daijia.rules.mapper.ProfitsharingRuleMapper;
import top.nuanyang26.daijia.rules.service.ProfitsharingRuleService;
import top.nuanyang26.daijia.rules.utils.DroolsHelper;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProfitsharingRuleServiceImpl implements ProfitsharingRuleService {

    private static final String RULES_CUSTOMER_RULES_DRL = "rules/ProfitsharingRule.drl";
    @Autowired
    private ProfitsharingRuleMapper rewardRuleMapper;

    @Override
    public ProfitsharingRuleResponseVo calculateOrderProfitsharingFee(ProfitsharingRuleRequestForm profitsharingRuleRequestForm) {
        //传入参数对象封装
        ProfitsharingRuleRequest profitsharingRuleRequest = new ProfitsharingRuleRequest();
        profitsharingRuleRequest.setOrderAmount(profitsharingRuleRequestForm.getOrderAmount());
        profitsharingRuleRequest.setOrderNum(profitsharingRuleRequestForm.getOrderNum());

        //创建kieSession
        KieSession kieSession = DroolsHelper.loadForRule(RULES_CUSTOMER_RULES_DRL);

        //封装返回对象
        ProfitsharingRuleResponse profitsharingRuleResponse = new ProfitsharingRuleResponse();
        kieSession.setGlobal("profitsharingRuleResponse",profitsharingRuleResponse);

        //触发规则，返回vo对象
        kieSession.insert(profitsharingRuleRequest);
        kieSession.fireAllRules();
        kieSession.dispose();

        ProfitsharingRuleResponseVo profitsharingRuleResponseVo = new ProfitsharingRuleResponseVo();
        BeanUtils.copyProperties(profitsharingRuleResponse,profitsharingRuleResponseVo);

        return profitsharingRuleResponseVo;
    }
}
