package top.nuanyang26.daijia.driver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.nuanyang26.daijia.common.execption.TonyException;
import top.nuanyang26.daijia.common.result.ResultCodeEnum;
import top.nuanyang26.daijia.driver.client.DriverInfoFeignClient;
import top.nuanyang26.daijia.driver.service.LocationService;
import top.nuanyang26.daijia.map.client.LocationFeignClient;
import top.nuanyang26.daijia.model.entity.driver.DriverSet;
import top.nuanyang26.daijia.model.form.map.OrderServiceLocationForm;
import top.nuanyang26.daijia.model.form.map.UpdateDriverLocationForm;
import top.nuanyang26.daijia.model.form.map.UpdateOrderLocationForm;

import java.util.List;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationFeignClient locationFeignClient;

    @Autowired
    private DriverInfoFeignClient driverInfoFeignClient;

    //更新司机位置
    @Override
    public Boolean updateDriverLocation(UpdateDriverLocationForm updateDriverLocationForm) {
        //开启接单了才能更新司机接单位置
        DriverSet driverSet = driverInfoFeignClient.getDriverSet(updateDriverLocationForm.getDriverId()).getData();
        if(driverSet.getServiceStatus().intValue() == 1) {
            return locationFeignClient.updateDriverLocation(updateDriverLocationForm).getData();
        } else {
            throw new TonyException(ResultCodeEnum.NO_START_SERVICE);
        }
        //根据司机id获取司机个性化设置信息
//        Long driverId = updateDriverLocationForm.getDriverId();
//        Result<DriverSet> driverSetResult = driverInfoFeignClient.getDriverSet(driverId);
//        DriverSet driverSet = driverSetResult.getData();
//
//        //判断：如果司机开始接单，更新位置信息
//        if(driverSet.getServiceStatus() == 1) {
//            Result<Boolean> booleanResult = locationFeignClient.updateDriverLocation(updateDriverLocationForm);
//            return booleanResult.getData();
//        } else {
//            //没有接单
//            throw new TonyException(ResultCodeEnum.NO_START_SERVICE);
//        }
    }

    @Override
    public Boolean updateOrderLocationToCache(UpdateOrderLocationForm updateOrderLocationForm) {
        return locationFeignClient.updateOrderLocationToCache(updateOrderLocationForm).getData();
    }

    @Override
    public Boolean saveOrderServiceLocation(List<OrderServiceLocationForm> orderLocationServiceFormList) {
        return locationFeignClient.saveOrderServiceLocation(orderLocationServiceFormList).getData();
    }
}
