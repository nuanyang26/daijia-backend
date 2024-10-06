package top.nuanyang26.daijia.driver.service;

import org.springframework.web.multipart.MultipartFile;
import top.nuanyang26.daijia.model.vo.driver.DriverLicenseOcrVo;
import top.nuanyang26.daijia.model.vo.driver.IdCardOcrVo;

public interface OcrService {

    //身份证识别
    IdCardOcrVo idCardOcr(MultipartFile file);

    //驾驶证识别
    DriverLicenseOcrVo driverLicenseOcr(MultipartFile file);
}
