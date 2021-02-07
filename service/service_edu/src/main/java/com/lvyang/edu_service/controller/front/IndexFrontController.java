package com.lvyang.edu_service.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.edu_service.entity.EduCourseInfo;
import com.lvyang.edu_service.entity.EduTeacher;
import com.lvyang.edu_service.service.EduCourseInfoService;
import com.lvyang.edu_service.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/15 11:20
 * @Version: 1.0
 */
//@CrossOrigin
@RestController
@RequestMapping("/edu_service/indexfront")
@Api(tags = {"07-Front Index Management"})
public class IndexFrontController {

    final EduCourseInfoService eduCourseInfoService;
    final EduTeacherService eduTeacherService;
    @Autowired
    private IndexFrontController(EduCourseInfoService eduCourseInfoService,EduTeacherService eduTeacherService){
        this.eduCourseInfoService = eduCourseInfoService;
        this.eduTeacherService = eduTeacherService;
    }



    /**
     * 查询前8条热门课程,查询前4条名师
     */
    @GetMapping("index")
    public JsonResultUnity index() {
        //查询前8条热门课程
        QueryWrapper<EduCourseInfo> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourseInfo> indexCourseList = eduCourseInfoService.list(wrapper);
        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("level");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> indexTeacherList = eduTeacherService.list(wrapperTeacher);
        //返回结果
        return JsonResultUnity.correct().data("indexCourseList",indexCourseList).data("indexTeacherList",indexTeacherList);

    }
    /**
     *
     */


}
