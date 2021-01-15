package com.lvyang.cms_service.service.impl;

import com.lvyang.cms_service.entity.CmsBanner;
import com.lvyang.cms_service.mapper.CmsBannerMapper;
import com.lvyang.cms_service.service.CmsBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author lvyang
 * @since 2020-12-20
 */
@Service
public class CmsBannerServiceImpl extends ServiceImpl<CmsBannerMapper, CmsBanner> implements CmsBannerService {

    @Override
    public List<CmsBanner> selectAllBanner() {
        List<CmsBanner> cmsBanners = baseMapper.selectList(null);
        return cmsBanners;
    }
}
