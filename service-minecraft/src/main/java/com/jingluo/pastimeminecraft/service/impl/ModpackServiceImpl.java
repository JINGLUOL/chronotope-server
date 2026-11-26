package com.jingluo.pastimeminecraft.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingluo.pastimeminecraft.dto.req.ModpackReq;
import com.jingluo.pastimeminecraft.entity.Modpack;
import com.jingluo.pastimeminecraft.mapper.ModpackMapper;
import com.jingluo.pastimeminecraft.service.ModpackService;
import com.jingluo.pastimeminecraft.util.do_converter.ModpackConverter;
import org.springframework.stereotype.Service;

@Service
public class ModpackServiceImpl
        extends ServiceImpl<ModpackMapper, Modpack>
        implements ModpackService {

    private final ModpackConverter converter;

    public ModpackServiceImpl(ModpackConverter converter) {
        this.converter = converter;
    }

    @Override
    public boolean saveModpack(Long userId, ModpackReq request) {
        Modpack modpack = converter.toEntity(request);
        modpack.setUserId(userId);
        return save(modpack);
    }

}
