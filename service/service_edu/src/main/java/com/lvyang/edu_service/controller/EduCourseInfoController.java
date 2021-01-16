package com.lvyang.edu_service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.edu_service.entity.EduCourseInfo;
import com.lvyang.edu_service.entity.vo.CourseInfoVO;
import com.lvyang.edu_service.entity.vo.CoursePublishVO;
import com.lvyang.edu_service.entity.vo.CourseQuery;
import com.lvyang.edu_service.service.EduCourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * <p>
 * 课程基本信息 前端控制器
 * </p>
 *
 * @author lvyang
 * @since 2020-12-10
 */
@RestController
@RequestMapping("/edu_service/edu_course_info")
@CrossOrigin
public class EduCourseInfoController {
    /**
     * 服务注入
     */
    private final EduCourseInfoService eduCourseInfoService;
    @Autowired
    public EduCourseInfoController (EduCourseInfoService eduCourseInfoService){
        this.eduCourseInfoService = eduCourseInfoService;
    }
    
    /**
     *添加课程基本信息
     */
    @PostMapping("createCourseInfo")
    public JsonResultUnity createCourseInfo(@RequestBody CourseInfoVO courseInfoVO) {
        String id = eduCourseInfoService.saveCourseInfo(courseInfoVO);
        return JsonResultUnity.correct().data("courseId", id);
    }

    /**
     * 根据课程查询课程基本信息
     */
    @GetMapping("retrieveCourseInfo/{courseId}")
    public JsonResultUnity retrieveCourseInfo(@PathVariable String courseId) {
        CourseInfoVO courseInfoVO = eduCourseInfoService.getCourseInfo(courseId);
        return JsonResultUnity.correct().data("courseInfo", courseInfoVO);

    }

    /**
     * 修改课程信息
     */
    @PostMapping("updateCourseInfo")
    public JsonResultUnity updateCourseInfo(@RequestBody CourseInfoVO courseInfoVO) {
        eduCourseInfoService.updateCourseInfo(courseInfoVO);
        return JsonResultUnity.correct();
    }

    /**
     * 根据课程Id查询课程确认信息
     */
    @GetMapping("getPublishCourseInfo/{courseId}")
    public JsonResultUnity getPublishCourseInfo(@PathVariable String courseId){
        CoursePublishVO coursePublishVO = eduCourseInfoService.getpublishCourseInfo(courseId);
        return JsonResultUnity.correct().data("publishCourse", coursePublishVO);
    }

    /**
     * 课程最终发布修改课程状态
     */
    @PostMapping("publishCourse/{courseId}")
    public JsonResultUnity publishCourse(@PathVariable String courseId){
        EduCourseInfo eduCourseInfo = new EduCourseInfo();
        eduCourseInfo.setId(courseId);
        eduCourseInfo.setStatus("Normal");
        eduCourseInfo.setIsDeleted(0);
        eduCourseInfoService.updateById(eduCourseInfo);
        return JsonResultUnity.correct();
    }

    /**
     * 课程列表带着分页
     */
    @GetMapping("retrieveCourseList")
    public JsonResultUnity retrieveCourseList(){
        List<EduCourseInfo> list = eduCourseInfoService.list(null);
        return JsonResultUnity.correct().data("CourseData", list);
    }

    /**
     * 分页查询课程的方法
     */
    @GetMapping("pageQueryCourse/{current}/{limit}")
    public JsonResultUnity pageQueryCourse(@PathVariable long current,@PathVariable long limit){
        Page<EduCourseInfo> pageQueryCourse = new Page<>(current, limit);
        eduCourseInfoService.page(pageQueryCourse, null);
        long total = pageQueryCourse.getTotal();
        List<EduCourseInfo> records = pageQueryCourse.getRecords();
        return JsonResultUnity.correct().data("total", total).data("rows", records);
    }
    /**
     * 条件查询带分页
     */
    @PostMapping("pageQueryCourseCondition/{current}/{limit}")
    public JsonResultUnity pageQueryCourseCondition(@PathVariable long current,@PathVariable long limit,@RequestBody(required = false) CourseQuery courseQuery){
        Page<EduCourseInfo> pageQueryCourse = new Page<>(current, limit);
        QueryWrapper<EduCourseInfo> wrapper = new QueryWrapper<>();
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        if (!(title == null || "".equals(title))){
            wrapper.like("title", title);
        }
        if(status !=null){
            wrapper.eq("status", status);
        }
        wrapper.orderByAsc("title");
        eduCourseInfoService.page(pageQueryCourse, wrapper);
        long total = pageQueryCourse.getTotal();
        List<EduCourseInfo> records = pageQueryCourse.getRecords();
        return JsonResultUnity.correct().data("total", total).data("rows", records);
    }

    /**
     * 删除课程
     */
    @DeleteMapping("{courseId}")
    public JsonResultUnity deleteCourse(@PathVariable String courseId){
        eduCourseInfoService.removeCourse(courseId);
        return JsonResultUnity.correct();
    }

}

