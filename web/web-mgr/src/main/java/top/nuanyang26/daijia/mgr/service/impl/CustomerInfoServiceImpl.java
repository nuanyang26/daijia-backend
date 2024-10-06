package top.nuanyang26.daijia.mgr.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.nuanyang26.daijia.customer.client.CustomerInfoFeignClient;
import top.nuanyang26.daijia.mgr.service.CustomerInfoService;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomerInfoServiceImpl implements CustomerInfoService {

	@Autowired
	private CustomerInfoFeignClient customerInfoFeignClient;



}
