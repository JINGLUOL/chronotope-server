package com.jingluo.pastimeminecraft.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingluo.pastimeminecraft.entity.ModTag;
import com.jingluo.pastimeminecraft.mapper.ModTagMapper;
import com.jingluo.pastimeminecraft.service.ModTagService;
import org.springframework.stereotype.Service;

@Service
public class ModTagServiceImpl
        extends ServiceImpl<ModTagMapper, ModTag>
        implements ModTagService {
}
