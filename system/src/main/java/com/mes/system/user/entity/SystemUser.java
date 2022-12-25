package com.mes.system.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("T_SYSTEM_USER")
public class SystemUser {
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;
    @TableField("USERNAME")
    private String username;
    @TableField("PASSWORD")
    private String password;
}
