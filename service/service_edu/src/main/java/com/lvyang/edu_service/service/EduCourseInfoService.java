package com.lvyang.edu_service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvyang.edu_service.entity.EduCourseInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lvyang.edu_service.entity.vo.CourseFrontVO;
import com.lvyang.edu_service.entity.vo.CourseInfoVO;
import com.lvyang.edu_service.entity.vo.CoursePublishVO;

import java.util.Map;

/**
 * <p>
 * 课程基本信息 服务类
 * </p>
 *
 * @author lvyang
 * @since 2020-12-10
 */
public interface EduCourseInfoService extends IService<EduCourseInfo> {

    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVO courseInfoVO);

    /**获取课程信息*/
    CourseInfoVO getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVO courseInfoVO);

    CoursePublishVO getpublishCourseInfo(String courseId);

    void removeCourse(String courseId);

    //条件查询带分页前端课程
    Map<String, Object> getFrontCoursePageList(Page<EduCourseInfo> pageParam, CourseFrontVO courseFrontVO);
}
