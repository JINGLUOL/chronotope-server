package com.jingluo.pastimeminecraft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingluo.pastimeminecraft.dto.req.ModpackReq;
import com.jingluo.pastimeminecraft.entity.Modpack;

public interface ModpackService extends IService<Modpack> {

    boolean saveModpack(Long userId, ModpackReq request);

}
