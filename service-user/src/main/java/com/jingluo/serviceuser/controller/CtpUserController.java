package com.jingluo.serviceuser.controller;

import com.jingluo.encryption.Cck;
import com.jingluo.serviceuser.constant.AuthProperties;
import com.jingluo.serviceuser.dto.req.CtpUserReq;
import com.jingluo.serviceuser.entity.CtpUser;
import com.jingluo.serviceuser.service.CtpUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;

@RestController
@RequestMapping("/user")
public class CtpUserController {

    private final CtpUserService ctpUserService;

    private final AuthProperties authProps;

    private final Clock clock;

    public CtpUserController(
            CtpUserService ctpUserService,
            AuthProperties authProps,
            Clock clock
    ) {
        this.ctpUserService = ctpUserService;
        this.authProps = authProps;
        this.clock = clock;
    }

    @Operation(summary = "用户注册", description = "根据请求体注册新用户；成功表示注册成功，否则用户已存在")
    @PostMapping("/reg")
    public ResponseEntity<Void> reg(@RequestBody CtpUserReq request) {
        return (ctpUserService.reg(request) ?
                ResponseEntity.ok() :
                ResponseEntity.badRequest()
        ).build();
    }

    @Operation(summary = "用户登录", description = "根据用户账号密码登录，响应头返回令牌，否则账号不存在或密码错误")
    @PostMapping("/sign")
    public ResponseEntity<Void> sign(@RequestBody CtpUserReq request) {
        CtpUser user = ctpUserService.sign(request);
        return (user != null ?
                ResponseEntity.ok().header(
                        "Authorization",
                        Cck.encryption(String.valueOf(user.getId()),
                                authProps.publicKey(), clock)
                ) : ResponseEntity.badRequest()
        ).build();
    }

    @Operation(summary = "获取账号封禁状态", description = "根据状态进行网关验证接口")
    @PostMapping("/ban-status")
    public Long whetherToBan(@RequestBody String token) {
        Long id = Cck.verify(token, authProps.publicKey(), clock, authProps.validDuration());
        if (id == null) return null;
        CtpUser user = ctpUserService.getById(id);
        if (user == null || user.getStatus() == -1) return -1L;
        return id;
    }

}
