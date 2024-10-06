package top.nuanyang26.daijia.mgr.service.impl;

import top.nuanyang26.daijia.mgr.service.SysMenuService;
import top.nuanyang26.daijia.model.entity.system.SysMenu;
import top.nuanyang26.daijia.model.vo.system.AssginMenuVo;
import top.nuanyang26.daijia.system.client.SysMenuFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuFeignClient sysMenuFeignClient;

    @Override
    public void save(SysMenu sysMenu) {
        sysMenuFeignClient.save(sysMenu);
    }

    @Override
    public void update(SysMenu sysMenu) {
        sysMenuFeignClient.update(sysMenu);
    }

    @Override
    public void remove(Long id) {
        sysMenuFeignClient.remove(id);
    }

    @Override
    public List<SysMenu> findNodes() {
        return sysMenuFeignClient.findNodes().getData();
    }

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        sysMenuFeignClient.doAssign(assginMenuVo);
    }

    @Override
    public List<SysMenu> toAssign(Long roleId) {
        return sysMenuFeignClient.toAssign(roleId).getData();
    }
}
