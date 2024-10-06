package top.nuanyang26.daijia.mgr.service;

import top.nuanyang26.daijia.model.entity.system.SysLoginLog;
import top.nuanyang26.daijia.model.query.system.SysLoginLogQuery;
import top.nuanyang26.daijia.model.vo.base.PageVo;

public interface SysLoginLogService {

    PageVo<SysLoginLog> findPage(Long page, Long limit, SysLoginLogQuery sysLoginLogQuery);

    /**
     * 记录登录信息
     *
     * @param sysLoginLog
     * @return
     */
    void recordLoginLog(SysLoginLog sysLoginLog);

    SysLoginLog getById(Long id);
}
