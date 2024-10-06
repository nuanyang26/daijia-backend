package top.nuanyang26.daijia.mgr.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.nuanyang26.daijia.driver.client.DriverInfoFeignClient;
import top.nuanyang26.daijia.mgr.service.DriverInfoService;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class DriverInfoServiceImpl implements DriverInfoService {

    @Autowired
    private DriverInfoFeignClient driverInfoFeignClient;



}