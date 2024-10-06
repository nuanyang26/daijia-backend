package top.nuanyang26.daijia.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.nuanyang26.daijia.model.entity.customer.CustomerInfo;
import top.nuanyang26.daijia.model.form.customer.UpdateWxPhoneForm;
import top.nuanyang26.daijia.model.vo.customer.CustomerLoginVo;

public interface CustomerInfoService extends IService<CustomerInfo> {

    //微信小程序登录接口
    Long login(String code);

    //获取客户登录信息
    CustomerLoginVo getCustomerInfo(Long customerId);

    //更新客户微信手机号码
    Boolean updateWxPhoneNumber(UpdateWxPhoneForm updateWxPhoneForm);

    String getCustomerOpenId(Long customerId);
}
