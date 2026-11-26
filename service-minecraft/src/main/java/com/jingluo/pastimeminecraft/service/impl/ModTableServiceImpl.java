package com.jingluo.pastimeminecraft.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingluo.pastimeminecraft.entity.ModTable;
import com.jingluo.pastimeminecraft.mapper.ModTableMapper;
import com.jingluo.pastimeminecraft.service.ModTableService;
import org.springframework.stereotype.Service;

@Service
public class ModTableServiceImpl
        extends ServiceImpl<ModTableMapper, ModTable>
        implements ModTableService {
}
