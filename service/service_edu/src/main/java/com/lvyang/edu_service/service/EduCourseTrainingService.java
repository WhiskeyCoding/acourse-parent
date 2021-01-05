package com.lvyang.edu_service.service;

import com.lvyang.edu_service.entity.EduCourseTraining;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频训练 服务类
 * </p>
 *
 * @author lvyang
 * @since 2020-12-10
 */
public interface EduCourseTrainingService extends IService<EduCourseTraining> {

    void removeTrainingByCourseId(String courseId);
}
