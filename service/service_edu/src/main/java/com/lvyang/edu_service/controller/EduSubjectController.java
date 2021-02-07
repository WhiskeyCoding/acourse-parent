package com.lvyang.edu_service.controller;


import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.edu_service.entity.subject.OneSubject;
import com.lvyang.edu_service.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author lvyang
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/edu_service/subject")
@Api(tags = {"02-Introduction Of Course Subjects"})
//@CrossOrigin
public class EduSubjectController {
    @Resource
    private EduSubjectService subjectService;

    //添加课程分类
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("importSubject")
    public JsonResultUnity importSubject(MultipartFile file) {
        subjectService.saveSubject(file, subjectService);
        return JsonResultUnity.correct();
    }

    @ApiOperation(value = "获取课程列表")
    @GetMapping("getAllSubject")
    public JsonResultUnity getAllSubject(){
        List<OneSubject> subjectList = subjectService.getAllOneTwoSubject();
        return JsonResultUnity.correct().data("subjectList",subjectList);
    }

}

