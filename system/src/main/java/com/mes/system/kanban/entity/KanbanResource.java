package com.mes.system.kanban.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@TableName("T_SYSTEM_KANBAN_RESOURCE")
@Data
public class KanbanResource {
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;
    @TableField("CREATE_TIME")
    private Date createTime;
    @NotBlank(message = "资源类型不能为空")
    @TableField("RESOURCE_TYPE")
    private String type;
    @NotBlank(message = "资源名称不能为空")
    @TableField("RESOURCE_NAME")
    private String name;
    @NotBlank(message = "资源位置不能为空")
    @TableField("RESOURCE_LOCATION")
    private String location;
    @TableField("RESOURCE_COUNT")
    private Integer count;
    @TableField("RESOURCE_DURATION")
    private Integer duration;
    @TableField("FACTORY")
    private String factory;
}
