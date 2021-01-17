package com.lvyang.cms_service.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvyang.cms_service.entity.CmsBanner;
import com.lvyang.cms_service.service.CmsBannerService;
import com.lvyang.common_utils.JsonResultUnity;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author lvyang
 * @since 2020-12-20
 */
@RestController
@RequestMapping("/cms_service/cms_banner_admin")
@CrossOrigin
public class CmsBannerAdminController {
    /**
     * 注入CmsBannerService服务
     */
    @Autowired
    private CmsBannerService cmsBannerService;

    /**
     * 分页查询Banner列表
     *
     * @param current  当前页码
     * @param limit 每页记录数
     * @return records-记录对象;total-总记录数;
     */
    @ApiOperation(value = "分页查询Banner列表")
    @GetMapping("paginationQueryToBanner/{current}/{limit}")
    public JsonResultUnity paginationQueryToBanner(@PathVariable long current, @PathVariable long limit) {
        Page<CmsBanner> cmsBannerPage = new Page<>(current, limit);
        cmsBannerService.page(cmsBannerPage, null);
        long total = cmsBannerPage.getTotal();
        List<CmsBanner> records = cmsBannerPage.getRecords();
        return JsonResultUnity.correct().data("total",total).data("records", records);
    }


    /**
     * 根据ID查询Banner
     *
     * @param id Banner的Id码
     * @return record-Banner的记录对象;
     */
    @ApiOperation(value = "根据ID查询Banner")
    @GetMapping("getBannerById/{id}")
    public JsonResultUnity getBannerById(@PathVariable String id) {
        CmsBanner cmsBanner = cmsBannerService.getById(id);
        return JsonResultUnity.correct().data("record", cmsBanner);
    }

    /**
     * 新建Banner
     *
     * @param cmsBanner Banner对象
     * @return JsonResultUnity
     */
    @ApiOperation(value = "新建Banner")
    @PostMapping("addBanner")
    public JsonResultUnity addBanner(@RequestBody CmsBanner cmsBanner) {
        cmsBannerService.save(cmsBanner);
        return JsonResultUnity.correct();
    }

    /**
     * 修改Banner
     *
     * @param cmsBanner Banner对象
     * @return JsonResultUnity
     */
    @ApiOperation(value = "修改Banner")
    @PutMapping("updateBanner")
    public JsonResultUnity updateBanner(@RequestBody CmsBanner cmsBanner) {
        cmsBannerService.updateById(cmsBanner);
        return JsonResultUnity.correct();
    }

    /**
     * 删除Banner
     *
     * @param id Banner的Id码
     * @return JsonResultUnity
     */
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("deleteBannerById/{id}")
    public JsonResultUnity deleteBannerById(@PathVariable String id) {
        cmsBannerService.removeById(id);
        return JsonResultUnity.correct();
    }
}

