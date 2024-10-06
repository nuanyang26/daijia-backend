package top.nuanyang26.daijia.system.service;

import top.nuanyang26.daijia.model.entity.system.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysDeptService extends IService<SysDept> {

    List<SysDept> findNodes();

    List<SysDept> findUserNodes();

    void updateStatus(Long id, Integer status);
}
