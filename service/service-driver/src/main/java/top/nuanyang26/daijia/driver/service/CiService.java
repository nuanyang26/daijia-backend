package top.nuanyang26.daijia.driver.service;

import top.nuanyang26.daijia.model.vo.order.TextAuditingVo;

public interface CiService {

    //图片审核
    Boolean imageAuditing(String path);

    TextAuditingVo textAuditing(String content);
}
