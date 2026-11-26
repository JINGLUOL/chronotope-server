package com.jingluo.pastimeminecraft.util.do_converter;

import com.jingluo.pastimeminecraft.dto.req.ModpackReq;
import com.jingluo.pastimeminecraft.entity.Modpack;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring") // 让 MapStruct 生成的实现类成为 Spring Bean
public interface ModpackConverter {

    Modpack toEntity(ModpackReq request);

    void updateEntity(ModpackReq request, @MappingTarget Modpack user);

}
