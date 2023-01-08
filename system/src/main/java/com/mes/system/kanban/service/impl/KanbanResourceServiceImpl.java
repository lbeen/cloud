package com.mes.system.kanban.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mes.mvc.pojo.Page;
import com.mes.mvc.utils.PageUtils;
import com.mes.system.kanban.entity.KanbanResource;
import com.mes.system.kanban.mapper.KanbanResourceMapper;
import com.mes.system.kanban.service.KanbanResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class KanbanResourceServiceImpl extends ServiceImpl<KanbanResourceMapper, KanbanResource> implements KanbanResourceService {
    @Override
    public Page queryResourcePage(Map<String, Object> param) {
        return PageUtils.getPage(this.baseMapper::queryResourceCount, this.baseMapper::queryResourcePage, param);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveResources(KanbanResource resource) {
        saveOrUpdate(resource);
    }
}
