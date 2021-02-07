package com.lvyang.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvyang.edu_service.entity.EduCourseTraining;
import com.lvyang.edu_service.mapper.EduCourseTrainingMapper;
import com.lvyang.edu_service.nacosclient.VodClient;
import com.lvyang.edu_service.service.EduCourseTrainingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频训练 服务实现类
 * </p>
 *
 * @author lvyang
 * @since 2020-12-10
 */
@Service
public class EduCourseTrainingServiceImpl extends ServiceImpl<EduCourseTrainingMapper, EduCourseTraining> implements EduCourseTrainingService {
    @Autowired
    private VodClient vodClient;
    /**根据课程id删除小节*/
    //删除小节，删除对应视频文件
    @Override
    public void removeTrainingByCourseId(String courseId) {


        //1.根据课程查出所有视频id
        QueryWrapper<EduCourseTraining> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        wrapperVideo.select("video_source_id");
        List<EduCourseTraining> eduCourseTrainingVideoList = baseMapper.selectList(wrapperVideo);
        //List<EduCourseTraining>变成List<String>
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < eduCourseTrainingVideoList.size(); i++) {
            EduCourseTraining eduCourseTraining = eduCourseTrainingVideoList.get(i);
            String videoSourceId = eduCourseTraining.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                videoIds.add(videoSourceId);
            }
        }
        //根据多个ID值删除多个视频
        if (videoIds.size() > 0) {
            vodClient.deleteBatch(videoIds);
        }

        //删除小节
        QueryWrapper<EduCourseTraining> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
