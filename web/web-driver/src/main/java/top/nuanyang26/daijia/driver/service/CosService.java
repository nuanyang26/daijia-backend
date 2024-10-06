package top.nuanyang26.daijia.driver.service;

import org.springframework.web.multipart.MultipartFile;
import top.nuanyang26.daijia.model.vo.driver.CosUploadVo;

public interface CosService {

    //文件上传接口
    CosUploadVo uploadFile(MultipartFile file, String path);
}
