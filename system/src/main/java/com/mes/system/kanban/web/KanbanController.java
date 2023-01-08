package com.mes.system.kanban.web;

import com.google.common.collect.Maps;
import com.mes.common.utils.Result;
import com.mes.mvc.pojo.Page;
import com.mes.system.kanban.entity.KanbanResource;
import com.mes.system.kanban.service.KanbanResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("kanban")
@RequiredArgsConstructor
public class KanbanController {
    private final KanbanResourceService kanbanResourceService;

    /**
     * 获取看板资源
     */
    @GetMapping("queryResourcePage")
    public Result<Page> queryResourcePage(String factory, Integer type, String name, Integer page, Integer pageSize) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("factory", factory);
        param.put("type", type);
        param.put("name", "%" + name + "%");
        param.put("page", page);
        param.put("pageSize", pageSize);
        return Result.success(kanbanResourceService.queryResourcePage(param));
    }

    /**
     * 保存看板资源
     */
    @PostMapping("saveResource")
    public Result<?> saveResource(@RequestBody @Valid KanbanResource resource) {
        kanbanResourceService.saveResources(resource);
        return Result.success("保存成功");
    }
}
