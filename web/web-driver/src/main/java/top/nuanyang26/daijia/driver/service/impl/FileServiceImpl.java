package top.nuanyang26.daijia.driver.service.impl;

import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.nuanyang26.daijia.common.constant.SystemConstant;
import top.nuanyang26.daijia.common.execption.TonyException;
import top.nuanyang26.daijia.common.result.ResultCodeEnum;
import top.nuanyang26.daijia.driver.config.MinioProperties;
import top.nuanyang26.daijia.driver.service.FileService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class FileServiceImpl implements FileService {

    @Autowired
    private MinioProperties minioProperties;

    @Override
    public String upload(MultipartFile file) {
        try {
            // 创建一个Minio的客户端对象
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioProperties.getEndpointUrl())
                    .credentials(minioProperties.getAccessKey(), minioProperties.getSecreKey())
                    .build();
            String bucketName = minioProperties.getBucketName();
            // 判断桶是否存在
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {       // 如果不存在，那么此时就创建一个新的桶
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } else {  // 如果存在打印信息
                System.out.printf("Bucket '%s' already exists", bucketName);
            }

            // 设置存储对象名称
            String extFileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = new SimpleDateFormat("yyyyMMdd")
                    .format(new Date()) + "/" + UUID.randomUUID().toString().replace("-" , "") + extFileName;

            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .object(fileName)
                    .build();
            minioClient.putObject(putObjectArgs) ;

            // 生成预签名 URL
            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                    .method(io.minio.http.Method.GET)
                    .bucket(bucketName)
                    .object(fileName)
                    .expiry(SystemConstant.CAR_IMG_URL_TIMEOUT, TimeUnit.DAYS)
                    .build();

            String showUrl = minioClient.getPresignedObjectUrl(args);

            return showUrl;

        } catch (Exception e) {
            throw new TonyException(ResultCodeEnum.DATA_ERROR);
        }
    }
}
