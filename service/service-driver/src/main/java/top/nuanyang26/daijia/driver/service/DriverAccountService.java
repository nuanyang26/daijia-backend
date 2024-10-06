package top.nuanyang26.daijia.driver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.nuanyang26.daijia.model.entity.driver.DriverAccount;
import top.nuanyang26.daijia.model.form.driver.TransferForm;

public interface DriverAccountService extends IService<DriverAccount> {

    Boolean transfer(TransferForm transferForm);
}
