package com.jingluo.serviceuser.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "用户的请求参数")
@Data
public class CtpUserReq {
    @Schema(description = "[a-zA-Z0-9]{8,20}", example = "12345678",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "账号不能为空")
    @Size(min = 8, max = 20, message = "账号长度需在 8~20 之间")
    private String account;
    @Schema(description = "[a-zA-Z0-9'\"-_^&#]{8,20}", example = "12345678",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    @Schema(description = ".{2,20}", example = "hi")
    private String nickname;
    @Schema(description = ".{,300}", example = "i am human.")
    private String introduction;
    @Schema(description = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", example = "1@2.ab")
    private String email;
    @Schema(description = "^1[3-9]\\d{9}$", example = "1@2.3")
    private String phone;
}
