package com.jingluo.pastimeminecraft.controller;

import com.jingluo.pastimeminecraft.dto.req.ModpackReq;
import com.jingluo.pastimeminecraft.service.ModpackService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/mc/modpack")
public class ModpackController {

    private final ModpackService modpackService;

    public ModpackController(ModpackService modpackService) {
        this.modpackService = modpackService;
    }

    @Operation(summary = "添加整合包")
    @PostMapping("/set")
    public ResponseEntity<Boolean> addModpack(
            @RequestHeader("X-User-Agent") Long userId, ModpackReq request) {
        return Optional.of(modpackService.saveModpack(userId, request))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
