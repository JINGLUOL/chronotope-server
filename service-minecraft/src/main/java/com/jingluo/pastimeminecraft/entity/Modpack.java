package com.jingluo.pastimeminecraft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Modpack {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String introduction;

    private String game_version;

    private Integer mod_loader_id;

    private Long userId;
}
