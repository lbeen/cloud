package com.mes.system.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("T_SYSTEM_LOG")
public class SystemLog {
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;
    @TableField("SERVER")
    private String server;
    @TableField("SERVER_PORT")
    private String serverPort;
    @TableField("SERVER_IP")
    private String serverIP;
    @TableField("CLIENT_IP")
    private String clientIP;
    @TableField("OPERATE_USER")
    private String operateUser;
    @TableField("LOG_LEVEL")
    private int logLevel;
    @TableField("LOG_CONTENT")
    private String logContent;
}
