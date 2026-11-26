package com.jingluo.serviceuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingluo.serviceuser.util.do_converter.CtpUserConverter;
import com.jingluo.serviceuser.dto.req.CtpUserReq;
import com.jingluo.serviceuser.entity.CtpUser;
import com.jingluo.serviceuser.mapper.CtpUserMapper;
import com.jingluo.serviceuser.service.CtpUserService;
import org.springframework.stereotype.Service;

@Service
public class CtpUserServiceImpl
        extends ServiceImpl<CtpUserMapper, CtpUser>
        implements CtpUserService {

    private final CtpUserConverter ctpUserConverter;

    private final CtpUserMapper ctpUserMapper;

    public CtpUserServiceImpl(
            CtpUserConverter ctpUserConverter,
            CtpUserMapper ctpUserMapper
    ) {
        this.ctpUserConverter = ctpUserConverter;
        this.ctpUserMapper = ctpUserMapper;
    }

    @Override
    public Boolean reg(CtpUserReq request) {
        CtpUser user = ctpUserConverter.toEntity(request);
        try {
            return save(user);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public CtpUser sign(CtpUserReq request) {
        LambdaQueryWrapper<CtpUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CtpUser::getAccount, request.getAccount());
        wrapper.eq(CtpUser::getPassword, request.getPassword());
        return ctpUserMapper.selectOne(wrapper);
    }

}
