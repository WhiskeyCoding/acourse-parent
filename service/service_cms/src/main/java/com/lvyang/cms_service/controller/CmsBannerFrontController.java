package com.lvyang.cms_service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvyang.cms_service.entity.CmsBanner;
import com.lvyang.cms_service.service.CmsBannerService;
import com.lvyang.common_utils.JsonResultUnity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: lvyang
 * @Description:前台控制器
 * @Date: 2020/12/20 13:49
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/cms_service/cms_banner_front")
public class CmsBannerFrontController {

    final CmsBannerService cmsBannerService;

    @Autowired
    private CmsBannerFrontController(CmsBannerService cmsBannerService) {
        this.cmsBannerService = cmsBannerService;
    }

    /**
     * 查询所有的Banner
     * @return JsonResultUnity.correct().data("bannerList", bannerList)
     */
    @GetMapping("getAllBanner")
    public JsonResultUnity getAllBanner(){
        //根据Id进行降序排列，显示排列之后的前两条记录
        QueryWrapper<CmsBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //last方法，拼接sql语句
        wrapper.last("limit 2");
        List<CmsBanner> bannerList = cmsBannerService.selectAllBanner();
        return JsonResultUnity.correct().data("bannerList", bannerList);
    }
}
