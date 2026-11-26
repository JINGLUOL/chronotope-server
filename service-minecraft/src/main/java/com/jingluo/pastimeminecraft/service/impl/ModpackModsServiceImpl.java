package com.jingluo.pastimeminecraft.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingluo.pastimeminecraft.entity.ModpackMods;
import com.jingluo.pastimeminecraft.mapper.ModpackModsMapper;
import com.jingluo.pastimeminecraft.service.ModpackModsService;
import org.springframework.stereotype.Service;

@Service
public class ModpackModsServiceImpl
        extends ServiceImpl<ModpackModsMapper, ModpackMods>
        implements ModpackModsService {
}
