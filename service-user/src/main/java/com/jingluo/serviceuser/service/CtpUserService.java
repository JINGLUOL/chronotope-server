package com.jingluo.serviceuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingluo.serviceuser.dto.req.CtpUserReq;
import com.jingluo.serviceuser.entity.CtpUser;

public interface CtpUserService extends IService<CtpUser> {
    Boolean reg(CtpUserReq request);

    CtpUser sign(CtpUserReq request);
}
