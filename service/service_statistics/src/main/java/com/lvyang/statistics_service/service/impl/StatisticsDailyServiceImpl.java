package com.lvyang.statistics_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.statistics_service.entity.StatisticsDaily;
import com.lvyang.statistics_service.mapper.StatisticsDailyMapper;
import com.lvyang.statistics_service.nacosclient.PortalClient;
import com.lvyang.statistics_service.service.StatisticsDailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author lvyang
 * @since 2021-02-02
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Resource
    private PortalClient portalClient;
    @Override
    public void countMemberRegistered(String dayId) {
        //添加之前先删除已经重复已有的数据，再添加新的数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", dayId);
        baseMapper.delete(wrapper);
        //远程调用PortalClient
        JsonResultUnity result = portalClient.countRegisteredMember(dayId);
        Integer enrollmentOneDay = (Integer)result.getData().get("enrollmentOneDay");
        //把获取的数据添加到数据库中，统计分析表里的数据
        StatisticsDaily tableStatisticsDaily = new StatisticsDaily();
        tableStatisticsDaily.setDateCalculated(dayId);//统计日期
        tableStatisticsDaily.setRegisterNum(enrollmentOneDay);//注册人数
        tableStatisticsDaily.setVideoViewNum(RandomUtils.nextInt(100,200));
        tableStatisticsDaily.setLoginNum(RandomUtils.nextInt(100,200));
        tableStatisticsDaily.setCourseNum(RandomUtils.nextInt(100,200));
        //添加到数据库中
        baseMapper.insert(tableStatisticsDaily);
    }

    @Override
    public Map<String, Object> getDailyStatistics(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated", begin, end);
        wrapper.select("date_calculated", type);
        List<StatisticsDaily> statisticsDailyList = baseMapper.selectList(wrapper);
        String title = new String();
        List<String> date_calculatedList = new ArrayList<>();
        List<Integer> numDateList = new ArrayList<>();
        for (int i = 0; i < statisticsDailyList.size(); i++) {
            StatisticsDaily daily = statisticsDailyList.get(i);
            //封装日期list集合
            date_calculatedList.add(daily.getDateCalculated());
            //封装对应数量
            switch (type) {
                case "login_num":
                    title = "学员登录统计";
                    numDateList.add(daily.getLoginNum());
                    break;
                case "register_num":
                    title = "学员注册统计";
                    numDateList.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    title = "课程播放量统计";
                    numDateList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    title = "每日课程数统计";
                    numDateList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        //封装后的两个集合返回到接口
        Map<String, Object> map = new HashMap<>();
        map.put("date_calculatedList", date_calculatedList);
        map.put("numDataList", numDateList);
        map.put("title", title);
        return map;
    }
}
