package com.lvyang.cms_service.controller;

import com.lvyang.cms_service.entity.CmsBanner;
import com.lvyang.cms_service.service.CmsBannerService;
import com.lvyang.common_utils.JsonResultUnity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: lvyang
 * @Description:前台控制器
 * @Date: 2020/12/20 13:49
 * @Version: 1.0
 */
//@CrossOrigin
@RestController
@RequestMapping("/cms_service/cms_banner_front")
public class CmsBannerFrontController {

    @Resource
    private CmsBannerService cmsBannerService;

    /**
     * 查询所有的Banner
     * @return JsonResultUnity.correct().data("bannerList", bannerList)
     */
    @GetMapping("getAllBanner")
    public JsonResultUnity getAllBanner(){
        List<CmsBanner> bannerList = cmsBannerService.selectAllBanner();
        return JsonResultUnity.correct().data("bannerList", bannerList);
    }
}
