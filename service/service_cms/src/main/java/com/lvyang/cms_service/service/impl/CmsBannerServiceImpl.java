package com.lvyang.cms_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvyang.cms_service.entity.CmsBanner;
import com.lvyang.cms_service.mapper.CmsBannerMapper;
import com.lvyang.cms_service.service.CmsBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value="banner",key="'selectIndexList'")
    @Override
    public List<CmsBanner> selectAllBanner() {
        //根据Id进行降序排列，显示排列之后的前两条记录
        QueryWrapper<CmsBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //last方法，拼接sql语句
        wrapper.last("limit 5");
        List<CmsBanner> cmsBanners = baseMapper.selectList(null);
        return cmsBanners;
    }
}
