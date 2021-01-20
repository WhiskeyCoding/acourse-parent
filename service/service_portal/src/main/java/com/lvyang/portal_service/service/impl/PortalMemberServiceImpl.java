package com.lvyang.portal_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvyang.common_utils.jwtutil.JwtUtils;
import com.lvyang.common_utils.md5util.MD5;
import com.lvyang.portal_service.entity.PortalMember;
import com.lvyang.portal_service.entity.vo.LoginInfoVO;
import com.lvyang.portal_service.entity.vo.LoginVO;
import com.lvyang.portal_service.entity.vo.RegisterVO;
import com.lvyang.portal_service.mapper.PortalMemberMapper;
import com.lvyang.portal_service.service.PortalMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvyang.service_base.exceptionhandler.ACourseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author lvyang
 * @since 2021-01-20
 */
@Service
public class PortalMemberServiceImpl extends ServiceImpl<PortalMemberMapper, PortalMember> implements PortalMemberService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void registerMember(RegisterVO registerVO) {
        //获取注册信息，进行校验
        String nickname = registerVO.getNickname();
        String mobile = registerVO.getMobile();
        String password = registerVO.getPassword();
        String code = registerVO.getCode();
        //校验参数
        if(StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code)) {
            throw new ACourseException(20001,"非空校验失败，注册失败");
        }

        //校验校验验证码
        //从redis获取发送的验证码
        String mobleCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(mobleCode)) {
            throw new ACourseException(20001,"验证码校验失败，注册失败");
        }

        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new QueryWrapper<PortalMember>().eq("mobile", mobile));
        if(count.intValue() > 0) {
            throw new ACourseException(20001,"此手机号已经注册过了，注册失败");
        }

        //添加注册信息到数据库
        PortalMember member = new PortalMember();
        member.setNickname(nickname);
        member.setMobile(registerVO.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("DEFAULT_AVATAR");
        member.setAge(-1);
        member.setSex(0);
        member.setOpenid("DEFAULT_OPENID");
        member.setSign("DEFAULT_SIGN");
        this.save(member);
    }


    @Override
    public String loginMember(LoginVO loginVO) {
        //获取登录的手机和密码
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();

        //校验参数手机和密码
        if(StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(mobile)) {
            throw new ACourseException(20001,"error");
        }

        //获取会员
        PortalMember member = baseMapper.selectOne(new QueryWrapper<PortalMember>().eq("mobile", mobile));
        if(null == member) {
            throw new ACourseException(20001,"error");
        }

        //校验密码
        if(!MD5.encrypt(password).equals(member.getPassword())) {
            throw new ACourseException(20001,"error");
        }

        //校验是否被禁用
        if(member.getIsDisabled()) {
            throw new ACourseException(20001,"error");
        }

        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return token;

    }

    @Override
    public LoginInfoVO getLoginInfo(String memberId) {
        return null;
    }
}
