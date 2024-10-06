package top.nuanyang26.daijia.mgr.service;

import top.nuanyang26.daijia.model.entity.system.SysOperLog;
import top.nuanyang26.daijia.model.query.system.SysOperLogQuery;
import top.nuanyang26.daijia.model.vo.base.PageVo;

public interface SysOperLogService {

    PageVo<SysOperLog> findPage(Long page, Long limit, SysOperLogQuery sysOperLogQuery);

    /**
     * 保存系统日志记录
     */
    void saveSysLog(SysOperLog sysOperLog);

    SysOperLog getById(Long id);
}
