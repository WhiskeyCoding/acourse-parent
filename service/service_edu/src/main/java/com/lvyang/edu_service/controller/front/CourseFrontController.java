package com.lvyang.edu_service.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.edu_service.entity.EduCourseInfo;
import com.lvyang.edu_service.entity.cchapter.ChapterVO;
import com.lvyang.edu_service.entity.vo.CourseFrontShowVO;
import com.lvyang.edu_service.entity.vo.CourseFrontVO;
import com.lvyang.edu_service.service.EduCourseChapterService;
import com.lvyang.edu_service.service.EduCourseInfoService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;
import java.util.Map;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2021/1/27 23:31
 * @Version: 1.0
 */
@RestController
@RequestMapping("/edu_service/coursefront")
@CrossOrigin
public class CourseFrontController {
    final EduCourseInfoService courseInfoService;
    final EduCourseChapterService courseChapterService;
    @Autowired
    private CourseFrontController(EduCourseInfoService courseInfoService,EduCourseChapterService courseChapterService) {
        this.courseInfoService = courseInfoService;
        this.courseChapterService = courseChapterService;
    }

    //1 条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public JsonResultUnity getFrontCourseList(@ApiParam(name = "page", value = "当前页码", required = true)
                                              @PathVariable Long page,
                                              @ApiParam(name = "limit", value = "每页记录数", required = true)
                                              @PathVariable Long limit,
                                              @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                                              @RequestBody(required = false) CourseFrontVO courseFrontVO) {
        Page<EduCourseInfo> pageParam = new Page<EduCourseInfo>(page, limit);
        Map<String, Object> pageCourseMap = courseInfoService.getFrontCoursePageList(pageParam, courseFrontVO);
        if (pageCourseMap != null) {
            return JsonResultUnity.correct().data(pageCourseMap);
        } else {
            return JsonResultUnity.error().message("最后一页了。");
        }
    }

    //2 查询
    @GetMapping("getFrontCourseInfo/{courseId}")
    public JsonResultUnity getFrontCourseInfo(@PathVariable String courseId) {
        //根据课程Id，编写sql语句查询课程信息
        CourseFrontShowVO courseFrontShowVO = courseInfoService.getCourseBaseInfo(courseId);
        //根据课程Id，查询章节和小节
        List<ChapterVO> courseChapterTrainingInfo = courseChapterService.getCourseChapterTrainingByCourseId(courseId);

        return JsonResultUnity.correct().data("courseFrontShowVO", courseFrontShowVO).data("courseChapterTrainingInfo", courseChapterTrainingInfo);
    }
}
