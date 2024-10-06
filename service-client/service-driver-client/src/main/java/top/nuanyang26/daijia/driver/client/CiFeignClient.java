package top.nuanyang26.daijia.driver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.nuanyang26.daijia.common.result.Result;
import top.nuanyang26.daijia.model.vo.order.TextAuditingVo;

@FeignClient(value = "service-driver")
public interface CiFeignClient {

    /**
     * 文本审核
     * @param content
     * @return
     */
    @PostMapping("/ci/textAuditing")
    Result<TextAuditingVo> textAuditing(@RequestBody String content);
}