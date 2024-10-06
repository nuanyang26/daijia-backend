package top.nuanyang26.daijia.driver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.nuanyang26.daijia.common.result.Result;
import top.nuanyang26.daijia.driver.service.FileService;

@Tag(name = "上传管理接口")
@RestController
@RequestMapping("file")
public class FileController {

//    @Autowired
//    private CosService cosService;

    @Autowired
    private FileService fileService;

    //文件上传接口
//    @Operation(summary = "上传")
//    //@TonyLogin
//    @PostMapping("/upload")
//    public Result<String> upload(@RequestPart("file") MultipartFile file,
//                   @RequestParam(name = "path",defaultValue = "auth") String path) {
//        CosUploadVo cosUploadVo = cosService.uploadFile(file,path);
//        String showUrl = cosUploadVo.getShowUrl();
//        return Result.ok(showUrl);
//    }
//    使用下面的 上传到 minio 代替了

    @Operation(summary = "上传")
    @PostMapping("/upload")
    public Result<String> upload(@RequestPart("file") MultipartFile file) {
        String url = fileService.upload(file);
        return Result.ok(url);
    }

}
