package com.mes.system.kanban.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mes.mvc.pojo.Record;
import com.mes.system.kanban.entity.KanbanResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface KanbanResourceMapper extends BaseMapper<KanbanResource> {

    int queryResourceCount(Map<String, Object> param);

    List<Record> queryResourcePage(Map<String, Object> param);
}
