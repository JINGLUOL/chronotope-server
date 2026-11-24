package com.jingluo.serviceuser.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "用户登录请求参数")
@Data
public class SignReq {
    @Schema(description = "[a-zA-Z0-9]{8,20}", example = "12345678",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "账号不能为空")
    @Size(min = 8, max = 20, message = "账号长度需在 8~20 之间")
    private String account;
    @Schema(description = "[a-zA-Z0-9'\"-_^&#]{8,20}", example = "12345678",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
