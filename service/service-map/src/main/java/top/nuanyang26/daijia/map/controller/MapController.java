package top.nuanyang26.daijia.map.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.nuanyang26.daijia.common.result.Result;
import top.nuanyang26.daijia.map.service.MapService;
import top.nuanyang26.daijia.model.form.map.CalculateDrivingLineForm;
import top.nuanyang26.daijia.model.vo.map.DrivingLineVo;

@Slf4j
@Tag(name = "地图API接口管理")
@RestController
@RequestMapping("/map")
@SuppressWarnings({"unchecked", "rawtypes"})
public class MapController {

    @Autowired
    private MapService mapService;

    @Operation(summary = "计算驾驶线路")
    @PostMapping("/calculateDrivingLine")
    public Result<DrivingLineVo> calculateDrivingLine(@RequestBody CalculateDrivingLineForm
                                                                  calculateDrivingLineForm) {
        DrivingLineVo drivingLineVo = mapService.calculateDrivingLine(calculateDrivingLineForm);
        return Result.ok(drivingLineVo);
    }
}

