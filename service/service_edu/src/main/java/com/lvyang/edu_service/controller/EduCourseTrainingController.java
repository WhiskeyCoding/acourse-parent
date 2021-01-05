package com.lvyang.edu_service.controller;


import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.edu_service.entity.EduCourseTraining;
import com.lvyang.edu_service.nacosclient.VodClient;
import com.lvyang.edu_service.service.EduCourseTrainingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频训练 前端控制器
 * </p>
 *
 * @author lvyang
 * @since 2020-12-10
 */
@RestController
@RequestMapping("/edu_service/edu_course_training")
@CrossOrigin
public class EduCourseTrainingController {

    /**
     * 服务注入
     */
    @Autowired
    private EduCourseTrainingService eduCourseTrainingService;

    /**
     * 注入服务中心的远程调用别的微服务
     */
    @Autowired
    private VodClient vodClient;


    /**
     * 添加小节
     * @param eduCourseTraining
     * @return
     */
    @PostMapping("createTraining")
    public JsonResultUnity createTraining(@RequestBody EduCourseTraining eduCourseTraining) {
        eduCourseTrainingService.save(eduCourseTraining);
        return JsonResultUnity.correct();
    }


    /**
     * 根据课程ID获取课程小节信息
     * @param trainingId
     * @return
     */
    @GetMapping("getCourseTraining/{trainingId}")
    public JsonResultUnity getCourseTraining(@PathVariable String trainingId) {
        EduCourseTraining eduCourseTraining = eduCourseTrainingService.getById(trainingId);
        return JsonResultUnity.correct().data("training", eduCourseTraining);
    }

    /**
     * 修改小节
     * @param eduCourseTraining
     * @return
     */
    @PostMapping("updateTraining")
    public JsonResultUnity updateTraining(@RequestBody EduCourseTraining eduCourseTraining) {
        eduCourseTrainingService.updateById(eduCourseTraining);
        return JsonResultUnity.correct();
    }

    /**
     * 删除小节,删除小节的时候,小节里面的视频也删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public JsonResultUnity deleteTraining(@PathVariable String id) {
        //根据小节获取ID
        EduCourseTraining eduCourseTraining = eduCourseTrainingService.getById(id);
        String videoSourceId = eduCourseTraining.getVideoSourceId();
        //先删视频
        if (!StringUtils.isEmpty(videoSourceId)) {
            vodClient.removeAliyunVideo(videoSourceId);
        }
        //再删除小节
        eduCourseTrainingService.removeById(id);
        return JsonResultUnity.correct();
    }

}

