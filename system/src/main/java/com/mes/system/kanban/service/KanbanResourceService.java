package com.mes.system.kanban.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mes.mvc.pojo.Page;
import com.mes.system.kanban.entity.KanbanResource;

import java.util.Map;

public interface KanbanResourceService extends IService<KanbanResource> {
    Page queryResourcePage(Map<String, Object> param);

    void saveResource(KanbanResource resource);

    void deleteResource(String id);
}
