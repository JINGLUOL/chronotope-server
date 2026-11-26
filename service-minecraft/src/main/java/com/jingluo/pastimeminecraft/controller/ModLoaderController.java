package com.jingluo.pastimeminecraft.controller;

import com.jingluo.pastimeminecraft.entity.ModLoader;
import com.jingluo.pastimeminecraft.service.ModLoaderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mc/mod-loader")
public class ModLoaderController {

    private final ModLoaderService modLoaderService;

    public ModLoaderController(ModLoaderService modLoaderService) {
        this.modLoaderService = modLoaderService;
    }

    @GetMapping("/all")
    public List<ModLoader> getAllModLoader() {
        return modLoaderService.list();
    }

    @Operation(summary = "添加加载器")
    @PostMapping("/set-one")
    public ResponseEntity<Boolean> setModLoader(@RequestBody String modLoader) {
        return Optional.of(modLoaderService.save(new ModLoader(modLoader)))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @Operation(summary = "批量添加加载器")
    @PostMapping("/set-batch")
    public ResponseEntity<Boolean> setModLoader(@RequestBody List<String> modLoaders) {
        return Optional.of(modLoaderService.saveBatch(
                        modLoaders.stream().map(ModLoader::new).collect(Collectors.toList())
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
