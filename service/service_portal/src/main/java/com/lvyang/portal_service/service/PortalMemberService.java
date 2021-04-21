package com.lvyang.portal_service.service;

import com.lvyang.portal_service.entity.PortalMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lvyang.portal_service.entity.vo.LoginInfoVO;
import com.lvyang.portal_service.entity.vo.LoginVO;
import com.lvyang.portal_service.entity.vo.RegisterVO;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author lvyang
 * @since 2021-01-20
 */
public interface PortalMemberService extends IService<PortalMember> {

    void registerMember(RegisterVO registerVO);

    String loginMember(LoginVO loginVO);

    LoginInfoVO getLoginInfo(String memberId);

    Integer countRegisteredMemberByDay(String dayId);

    void removeMember(String memberId);
}
