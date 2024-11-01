package top.nuanyang26.daijia.customer.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.nuanyang26.daijia.common.execption.TonyException;
import top.nuanyang26.daijia.common.result.ResultCodeEnum;
import top.nuanyang26.daijia.customer.mapper.CustomerInfoMapper;
import top.nuanyang26.daijia.customer.mapper.CustomerLoginLogMapper;
import top.nuanyang26.daijia.customer.service.CustomerInfoService;
import top.nuanyang26.daijia.model.entity.customer.CustomerInfo;
import top.nuanyang26.daijia.model.entity.customer.CustomerLoginLog;
import top.nuanyang26.daijia.model.form.customer.UpdateWxPhoneForm;
import top.nuanyang26.daijia.model.vo.customer.CustomerLoginVo;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoMapper, CustomerInfo> implements CustomerInfoService {

    @Autowired
    private WxMaService wxMaService;
    
    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Autowired
    private CustomerLoginLogMapper customerLoginLogMapper;

    //微信小程序登录接口
    @Override
    public Long login(String code) {
        //1 获取code值，使用微信工具包对象，获取微信唯一标识openid
        String openid = null;
        try {
            WxMaJscode2SessionResult sessionInfo =
                    wxMaService.getUserService().getSessionInfo(code);
            openid = sessionInfo.getOpenid();
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }

        //2 根据openid查询数据库表，判断是否第一次登录
        //如果openid不存在返回null，如果存在返回一条记录
        //select * from customer_info ci where ci.wx_open_id = ''
        LambdaQueryWrapper<CustomerInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CustomerInfo::getWxOpenId,openid);
        CustomerInfo customerInfo = customerInfoMapper.selectOne(wrapper);

        //3 如果第一次登录，添加信息到用户表
        if(customerInfo == null) {
            customerInfo = new CustomerInfo();
            customerInfo.setNickname(String.valueOf(System.currentTimeMillis()));
            customerInfo.setAvatarUrl("https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
            customerInfo.setWxOpenId(openid);
            customerInfoMapper.insert(customerInfo);
        }

        //4 记录登录日志信息
        CustomerLoginLog customerLoginLog = new CustomerLoginLog();
        customerLoginLog.setCustomerId(customerInfo.getId());
        customerLoginLog.setMsg("小程序登录");
        customerLoginLogMapper.insert(customerLoginLog);

        //5 返回用户id
        return customerInfo.getId();
    }

    //获取客户登录信息
    @Override
    public CustomerLoginVo getCustomerInfo(Long customerId) {
        //1 根据用户id查询用户信息
        CustomerInfo customerInfo = customerInfoMapper.selectById(customerId);

        //2 封装到CustomerLoginVo
        CustomerLoginVo customerLoginVo = new CustomerLoginVo();
        //customerLoginVo.setNickname(customerInfo.getNickname());
        BeanUtils.copyProperties(customerInfo,customerLoginVo);

        //@Schema(description = "是否绑定手机号码")
        //    private Boolean isBindPhone;
        String phone = customerInfo.getPhone();
        boolean isBindPhone = StringUtils.hasText(phone);
        customerLoginVo.setIsBindPhone(isBindPhone);

        //3 CustomerLoginVo返回
        return customerLoginVo;
    }

    ////更新客户微信手机号码
    @Override
    public Boolean updateWxPhoneNumber(UpdateWxPhoneForm updateWxPhoneForm) {
        //1 根据code值获取微信绑定手机号码
        try {
            WxMaPhoneNumberInfo phoneNoInfo =
                    wxMaService.getUserService().getPhoneNoInfo(updateWxPhoneForm.getCode());
            String phoneNumber = phoneNoInfo.getPhoneNumber();

            //更新用户信息
            Long customerId = updateWxPhoneForm.getCustomerId();
            CustomerInfo customerInfo = customerInfoMapper.selectById(customerId);
            customerInfo.setPhone(phoneNumber);
            customerInfoMapper.updateById(customerInfo);

            return true;
        } catch (WxErrorException e) {
            throw new TonyException(ResultCodeEnum.DATA_ERROR);
        }
    }

    @Override
    public String getCustomerOpenId(Long customerId) {
        LambdaQueryWrapper<CustomerInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CustomerInfo::getId,customerId);
        CustomerInfo customerInfo = customerInfoMapper.selectOne(wrapper);
        return customerInfo.getWxOpenId();
    }

}
