package com.jingluo.pastimeminecraft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ModLoader {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    public ModLoader(String modName) {
        this.name = modName;
    }

}
