package top.nuanyang26.daijia.mgr.service;


import top.nuanyang26.daijia.model.entity.system.SysUser;
import top.nuanyang26.daijia.model.query.system.SysUserQuery;
import top.nuanyang26.daijia.model.vo.base.PageVo;

public interface SysUserService {

    SysUser getById(Long id);

    void save(SysUser sysUser);

    void update(SysUser sysUser);

    void remove(Long id);

    PageVo<SysUser> findPage(Long page, Long limit, SysUserQuery sysUserQuery);

    void updateStatus(Long id, Integer status);


}
