package com.jingluo.pastimeminecraft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class ModpackMods {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long modId;

    private String modVersion;

    private List<Integer> tagIds;

    private Long modpackId;
}
