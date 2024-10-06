package top.nuanyang26.daijia.map.service;

import top.nuanyang26.daijia.model.form.map.CalculateDrivingLineForm;
import top.nuanyang26.daijia.model.vo.map.DrivingLineVo;

public interface MapService {

    //计算驾驶线路
    DrivingLineVo calculateDrivingLine(CalculateDrivingLineForm calculateDrivingLineForm);
}
