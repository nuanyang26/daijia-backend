package top.nuanyang26.daijia.driver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.nuanyang26.daijia.driver.client.CiFeignClient;
import top.nuanyang26.daijia.driver.service.FileService;
import top.nuanyang26.daijia.driver.service.MonitorService;
import top.nuanyang26.daijia.model.entity.order.OrderMonitorRecord;
import top.nuanyang26.daijia.model.form.order.OrderMonitorForm;
import top.nuanyang26.daijia.model.vo.order.TextAuditingVo;
import top.nuanyang26.daijia.order.client.OrderMonitorFeignClient;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private FileService fileService;

    @Autowired
    private OrderMonitorFeignClient orderMonitorFeignClient;

    @Autowired
    private CiFeignClient ciFeignClient;

    @Override
    public Boolean upload(MultipartFile file, OrderMonitorForm orderMonitorForm) {
        //上传文件
        String url = fileService.upload(file);

        OrderMonitorRecord orderMonitorRecord = new OrderMonitorRecord();
        orderMonitorRecord.setOrderId(orderMonitorForm.getOrderId());
        orderMonitorRecord.setFileUrl(url);
        orderMonitorRecord.setContent(orderMonitorForm.getContent());
        //增加文本审核
        TextAuditingVo textAuditingVo =
                ciFeignClient.textAuditing(orderMonitorForm.getContent()).getData();
        orderMonitorRecord.setResult(textAuditingVo.getResult());
        orderMonitorRecord.setKeywords(textAuditingVo.getKeywords());

        orderMonitorFeignClient.saveMonitorRecord(orderMonitorRecord);
        return true;
    }
}
