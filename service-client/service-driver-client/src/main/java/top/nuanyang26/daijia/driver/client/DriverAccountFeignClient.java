package top.nuanyang26.daijia.driver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.nuanyang26.daijia.common.result.Result;
import top.nuanyang26.daijia.model.form.driver.TransferForm;

@FeignClient(value = "service-driver")
public interface DriverAccountFeignClient {

    /**
     * 转账
     * @param transferForm
     * @return
     */
    @PostMapping("/driver/account/transfer")
    Result<Boolean> transfer(@RequestBody TransferForm transferForm);
}