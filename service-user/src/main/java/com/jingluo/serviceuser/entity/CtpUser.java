package com.jingluo.serviceuser.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CtpUser {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String account;
    private String password;
    //  @TableField("`username`")
    private String nickname;
    private String introduction;
    private String email;
    private String phone;
    @TableField(fill = FieldFill.INSERT) // 插入时自动填充
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE) // 插入和更新时都填充
    private LocalDateTime updateTime;
    private Integer status;
}
