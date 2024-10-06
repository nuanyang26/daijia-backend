package top.nuanyang26.daijia.driver.service;

import org.springframework.web.multipart.MultipartFile;
import top.nuanyang26.daijia.model.vo.driver.CosUploadVo;

public interface CosService {

    CosUploadVo upload(MultipartFile file, String path);

    //获取临时签名URL
    String getImageUrl(String path);

}
