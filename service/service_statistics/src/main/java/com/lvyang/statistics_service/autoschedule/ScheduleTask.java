package com.lvyang.statistics_service.autoschedule;


import com.lvyang.common_utils.dateutil.DateUtil;
import com.lvyang.statistics_service.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/2/2 18:03
 * @Version: 1.0
 */
@Component
public class ScheduleTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;
    /**
     * 测试 每隔60秒执行一次
     */
    @Scheduled(cron = "0/60 * * * * ?")
    public void task1() {
        System.out.println("Schedule Task Per 60Seconds。");
    }

    /**
     * 在每天2000的时间中把前一天的数据进行查询添加
     */
    @Scheduled(cron = "0 0 20 * * ? ")
    public void taskOfStatistics(){
        String theDayBefore = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        statisticsDailyService.countMemberRegistered(theDayBefore);
        System.out.println("The data of the previous day will be queried and added in 2000 hours every day");
    }

}
