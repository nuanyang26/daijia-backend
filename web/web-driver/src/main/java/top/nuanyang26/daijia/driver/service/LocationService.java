package top.nuanyang26.daijia.driver.service;

import top.nuanyang26.daijia.model.form.map.OrderServiceLocationForm;
import top.nuanyang26.daijia.model.form.map.UpdateDriverLocationForm;
import top.nuanyang26.daijia.model.form.map.UpdateOrderLocationForm;

import java.util.List;

public interface LocationService {

    //更新司机位置
    Boolean updateDriverLocation(UpdateDriverLocationForm updateDriverLocationForm);

    Boolean updateOrderLocationToCache(UpdateOrderLocationForm updateOrderLocationForm);

    Boolean saveOrderServiceLocation(List<OrderServiceLocationForm> orderLocationServiceFormList);
}
