package com.lvyang.edu_service.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.edu_service.entity.EduCourseInfo;
import com.lvyang.edu_service.entity.EduTeacher;
import com.lvyang.edu_service.service.EduCourseInfoService;
import com.lvyang.edu_service.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/23 12:27
 * @Version: 1.0
 */
@RestController
@RequestMapping("/edu_service/teacherfront")
@Api(tags = {"09-Front Teacher Management"})
//@CrossOrigin
public class TeacherFrontController {
    final EduTeacherService eduTeacherService;
    final EduCourseInfoService eduCourseInfoService;
    @Autowired
    private TeacherFrontController(EduTeacherService eduTeacherService, EduCourseInfoService eduCourseInfoService) {
        this.eduTeacherService = eduTeacherService;
        this.eduCourseInfoService = eduCourseInfoService;
    }

    //1.分页查询教师的方法
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public JsonResultUnity getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {
        Page<EduTeacher> frontPageTeacher = new Page<>(page, limit);
        Map<String,Object> pageTeacherMap = eduTeacherService.getTeacherFrontList(frontPageTeacher);
        if (pageTeacherMap != null) {
            return JsonResultUnity.correct().data(pageTeacherMap);
        } else {
            return JsonResultUnity.error().message("最后一页了。");
        }
    }

    //2.讲师详情接口功能
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public JsonResultUnity getTeacherFrontInfo(@PathVariable String teacherId) {
        //1.根据讲师id查询讲师基本信息
        EduTeacher eduTeacher = eduTeacherService.getById(teacherId);
        //2.根据讲师id查询所讲课程
        QueryWrapper<EduCourseInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        List<EduCourseInfo> coursesOfTeacher = eduCourseInfoService.list(wrapper);
        return JsonResultUnity.correct().data("teacher", eduTeacher).data("courseList", coursesOfTeacher);

    }

}
