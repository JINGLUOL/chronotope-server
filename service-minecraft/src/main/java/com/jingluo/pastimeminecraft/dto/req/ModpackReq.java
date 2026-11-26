package com.jingluo.pastimeminecraft.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "整合包基础信息")
@Data
public class ModpackReq {
    @Schema(description = "\\.{1,100}", example = "名称",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "账号不能为空")
    @Size(min = 1, max = 100, message = "内容长度需在 8~20 之间")
    private String name;

    @Schema(description = "\\.{0,300}", example = "介绍")
    @Size(max = 300, message = "内容长度需在 8~20 之间")
    private String introduction;

    @Schema(description = "\\.{0,300}", example = "游戏版本号",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "游戏本体版本号不能为空")
    @Size(max = 50, message = "内容长度需在 8~20 之间")
    private String game_version;

    @Schema(description = "系统生成ID，请勿自填",
            accessMode = Schema.AccessMode.READ_ONLY)
    @NotBlank(message = "模组加载器ID不能为空")
    private Integer mod_loader_id;
}
