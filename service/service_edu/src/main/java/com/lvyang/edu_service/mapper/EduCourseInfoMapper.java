package com.lvyang.edu_service.mapper;

import com.lvyang.edu_service.entity.EduCourseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvyang.edu_service.entity.vo.CoursePublishVO;

/**
 * <p>
 * 课程基本信息 Mapper 接口
 * </p>
 *
 * @author lvyang
 * @since 2020-12-10
 */
public interface EduCourseInfoMapper extends BaseMapper<EduCourseInfo> {
    public CoursePublishVO getPublishCourseInfo(String courseId);
}
