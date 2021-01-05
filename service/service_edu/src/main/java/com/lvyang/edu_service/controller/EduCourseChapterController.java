package com.lvyang.edu_service.controller;


import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.edu_service.entity.EduCourseChapter;
import com.lvyang.edu_service.entity.cchapter.ChapterVO;
import com.lvyang.edu_service.service.EduCourseChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 课程章节 前端控制器
 * </p>
 *
 * @author lvyang
 * @since 2020-12-10
 */
@RestController
@RequestMapping("/edu_service/edu_course_chapter")
@CrossOrigin
public class EduCourseChapterController {
    /** EduCourseChapterService 服务注入 */
    @Autowired
    private EduCourseChapterService eduCourseChapterService;
    /** 根据课程ID获取课程章节小结 Get方法*/
    @GetMapping("getCourseChapterTraining/{courseId}")
    public JsonResultUnity getCourseChapterTraining(@PathVariable String courseId){
        List<ChapterVO> list = eduCourseChapterService.getCourseChapterTrainingByCourseId(courseId);
        return JsonResultUnity.correct().data("allCCT",list);
    }
    /** 创建课程章节信息 Post方法 */
    @PostMapping("createCourseChapter")
    public JsonResultUnity createCourseChapter(@RequestBody EduCourseChapter eduCourseChapter) {
        eduCourseChapterService.save(eduCourseChapter);
        return JsonResultUnity.correct();
    }
    /** 根据ID获取课程章节信息 Get方法*/
    @GetMapping("getCourseChapter/{chapterId}")
    public JsonResultUnity getCourseChapter(@PathVariable String chapterId){
        EduCourseChapter eduCourseChapter=eduCourseChapterService.getById(chapterId);
        return JsonResultUnity.correct().data("chapter", eduCourseChapter);
    }
    /** 修改课程章节信息 Post方法 */
    @PostMapping("updateChapter")
    public JsonResultUnity updateChapter(@RequestBody EduCourseChapter eduCourseChapter) {
        eduCourseChapterService.updateById(eduCourseChapter);
        return JsonResultUnity.correct();
    }
    /** 删除课程章节信息 Delete方法 */
    @DeleteMapping("{chapterId}")
    public JsonResultUnity deleteChapter(@PathVariable String chapterId){
        boolean flag = eduCourseChapterService.deleteChapter(chapterId);
        if(flag){
            return JsonResultUnity.correct();
        }else{
            return JsonResultUnity.error();
        }
    }
}

