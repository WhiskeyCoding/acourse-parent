package com.lvyang.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvyang.edu_service.entity.EduCourseChapter;
import com.lvyang.edu_service.entity.EduCourseTraining;
import com.lvyang.edu_service.entity.cchapter.ChapterVO;
import com.lvyang.edu_service.entity.cchapter.TrainingVO;
import com.lvyang.edu_service.mapper.EduCourseChapterMapper;
import com.lvyang.edu_service.service.EduCourseChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvyang.edu_service.service.EduCourseTrainingService;
import com.lvyang.service_base.exceptionhandler.ACourseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程章节 服务实现类
 * </p>
 *
 * @author lvyang
 * @since 2020-12-10
 */
@Service
public class EduCourseChapterServiceImpl extends ServiceImpl<EduCourseChapterMapper, EduCourseChapter> implements EduCourseChapterService {

    @Autowired
    private EduCourseTrainingService eduCourseTrainingService;
    @Override
    public List<ChapterVO> getCourseChapterTrainingByCourseId(String courseId) {
        //1.根据课程ID查询课程里面所有章节
        QueryWrapper<EduCourseChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        wrapperChapter.orderByAsc("sort");
        List<EduCourseChapter> eduCourseChapterList = baseMapper.selectList(wrapperChapter);
        //2.根据课程ID查询课程里面所有小节
        QueryWrapper<EduCourseTraining> wrapperTraining = new QueryWrapper<>();
        wrapperTraining.eq("course_id", courseId);
        wrapperTraining.orderByAsc("sort");
        List<EduCourseTraining> eduCourseTrainingList = eduCourseTrainingService.list(wrapperTraining);

        //创建list集合，用于最终封装数据
        List<ChapterVO> finalList = new ArrayList<>();
        //3.遍历查询章节List集合进行封装
        for (int i = 0; i < eduCourseChapterList.size(); i++) {
            EduCourseChapter eduCourseChapter = eduCourseChapterList.get(i);
            ChapterVO chapterVO = new ChapterVO();
            BeanUtils.copyProperties(eduCourseChapter,chapterVO);
            finalList.add(chapterVO);
            //4.遍历查询小节List集合进行封装
            List<TrainingVO> trainingVOList = new ArrayList<>();
            for (int j = 0; j < eduCourseTrainingList.size(); j++) {
                EduCourseTraining eduCourseTraining = eduCourseTrainingList.get(j);
                if (eduCourseTraining.getChapterId().equals(eduCourseChapter.getId())) {
                    TrainingVO trainingVO = new TrainingVO();
                    BeanUtils.copyProperties(eduCourseTraining, trainingVO);
                    trainingVOList.add(trainingVO);
                }
            }
            //5.封装之后小节list集合，放到章节对象里面
            chapterVO.setChildren(trainingVOList);
        }
        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterId章节id,查询小节表,如果查询到数据,不进行删除
        QueryWrapper<EduCourseTraining> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = eduCourseTrainingService.count(wrapper);
        //判断
        if (count > 0) {
            //查询出小节,不进行删除
            throw new ACourseException(20001, "不能进行删除");
        }else{
            //不能查询出小节,进行删除
            int result = baseMapper.deleteById(chapterId);
            return result>0;
        }
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduCourseChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
