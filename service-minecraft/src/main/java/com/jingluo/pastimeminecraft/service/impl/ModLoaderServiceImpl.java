package com.jingluo.pastimeminecraft.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingluo.pastimeminecraft.entity.ModLoader;
import com.jingluo.pastimeminecraft.mapper.ModLoaderMapper;
import com.jingluo.pastimeminecraft.service.ModLoaderService;
import org.springframework.stereotype.Service;

@Service
public class ModLoaderServiceImpl
        extends ServiceImpl<ModLoaderMapper, ModLoader>
        implements ModLoaderService {
}
