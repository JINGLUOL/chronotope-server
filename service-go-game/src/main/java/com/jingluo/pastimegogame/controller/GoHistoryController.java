package com.jingluo.pastimegogame.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/go-game")
public class GoHistoryController {

    @GetMapping("/1")
    public String a(@RequestHeader("X-User-Agent") Long userId) {
        return "suc";
    }

}
