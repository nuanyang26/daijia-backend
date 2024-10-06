package top.nuanyang26.daijia.driver.service;

import org.springframework.web.multipart.MultipartFile;
import top.nuanyang26.daijia.model.form.order.OrderMonitorForm;

public interface MonitorService {

    Boolean upload(MultipartFile file, OrderMonitorForm orderMonitorForm);
}
