package com.lvyang.statistics_service.controller;


import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.statistics_service.service.StatisticsDailyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author lvyang
 * @since 2021-02-02
 */
@RestController
@RequestMapping("/statistics_service/statistics_daily")
//@CrossOrigin
public class StatisticsDailyController {
    @Resource
    private StatisticsDailyService statisticsDailyService;

    /**
     * 统计某一天注册人数，生成统计数据
     * @param dayId
     * @return
     */
    @PostMapping("countTheNumberOfMemberRegistered/{dayId}")
    public JsonResultUnity countTheNumberOfMemberRegistered(@PathVariable String dayId) {
        statisticsDailyService.countMemberRegistered(dayId);
        return JsonResultUnity.correct();
    }

    @GetMapping("showDailyStatistics/{type}/{begin}/{end}")
    public JsonResultUnity showDailyStatistics(@PathVariable String type, @PathVariable String begin, @PathVariable String end) {
        Map<String, Object> map = statisticsDailyService.getDailyStatistics(type, begin, end);
        return JsonResultUnity.correct().data("dailyStatisticsData",map);
    }
}

