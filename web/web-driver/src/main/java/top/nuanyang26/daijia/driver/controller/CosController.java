package top.nuanyang26.daijia.driver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.nuanyang26.daijia.common.result.Result;
import top.nuanyang26.daijia.driver.service.CosService;
import top.nuanyang26.daijia.model.vo.driver.CosUploadVo;

@Slf4j
@Tag(name = "腾讯云cos上传接口管理")
@RestController
@RequestMapping(value="/cos")
@SuppressWarnings({"unchecked", "rawtypes"})
public class CosController {

    @Autowired
    private CosService cosService;

    //文件上传接口
    @Operation(summary = "上传")
    //@TonyLogin
    @PostMapping("/upload")
    public Result<CosUploadVo> upload(@RequestPart("file") MultipartFile file,
                                      @RequestParam(name = "path",defaultValue = "auth") String path) {
        CosUploadVo cosUploadVo = cosService.uploadFile(file,path);
        return Result.ok(cosUploadVo);
    }
}

