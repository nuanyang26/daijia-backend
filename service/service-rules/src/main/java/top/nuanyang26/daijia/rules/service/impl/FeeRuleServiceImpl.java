package top.nuanyang26.daijia.rules.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.nuanyang26.daijia.model.form.rules.FeeRuleRequest;
import top.nuanyang26.daijia.model.form.rules.FeeRuleRequestForm;
import top.nuanyang26.daijia.model.vo.rules.FeeRuleResponse;
import top.nuanyang26.daijia.model.vo.rules.FeeRuleResponseVo;
import top.nuanyang26.daijia.rules.service.FeeRuleService;
import top.nuanyang26.daijia.rules.utils.DroolsHelper;

import java.util.Date;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class FeeRuleServiceImpl implements FeeRuleService {

    private static final String RULES_CUSTOMER_RULES_DRL = "rules/FeeRule.drl";

    //计算订单费用
    @Override
    public FeeRuleResponseVo calculateOrderFee(FeeRuleRequestForm calculateOrderFeeForm) {

        //封装输入对象
        FeeRuleRequest feeRuleRequest = new FeeRuleRequest();
        feeRuleRequest.setDistance(calculateOrderFeeForm.getDistance());
        Date startTime = calculateOrderFeeForm.getStartTime();
        feeRuleRequest.setStartTime(new DateTime(startTime).toString("HH:mm:ss"));
        feeRuleRequest.setWaitMinute(calculateOrderFeeForm.getWaitMinute());

        //Drools使用
        KieSession kieSession = DroolsHelper.loadForRule(RULES_CUSTOMER_RULES_DRL);

        //封装返回对象
        FeeRuleResponse feeRuleResponse = new FeeRuleResponse();
        kieSession.setGlobal("feeRuleResponse",feeRuleResponse);

        kieSession.insert(feeRuleRequest);
        kieSession.fireAllRules();
        kieSession.dispose();

        //封装数据到FeeRuleResponseVo返回
        FeeRuleResponseVo feeRuleResponseVo = new FeeRuleResponseVo();
        // feeRuleResponse -- feeRuleResponseVo
        BeanUtils.copyProperties(feeRuleResponse,feeRuleResponseVo);
        return feeRuleResponseVo;
    }
}
