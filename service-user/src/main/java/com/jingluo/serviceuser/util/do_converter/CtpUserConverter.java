package com.jingluo.serviceuser.util.do_converter;

import com.jingluo.serviceuser.dto.req.CtpUserReq;
import com.jingluo.serviceuser.entity.CtpUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring") // 让 MapStruct 生成的实现类成为 Spring Bean
public interface CtpUserConverter {

    CtpUser toEntity(CtpUserReq request);

    void updateEntity(CtpUserReq request, @MappingTarget CtpUser user);

}
