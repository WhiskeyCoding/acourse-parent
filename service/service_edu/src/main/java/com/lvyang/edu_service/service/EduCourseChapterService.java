package com.lvyang.edu_service.service;

import com.lvyang.edu_service.entity.EduCourseChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lvyang.edu_service.entity.cchapter.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程章节 服务类
 * </p>
 *
 * @author lvyang
 * @since 2020-12-10
 */
public interface EduCourseChapterService extends IService<EduCourseChapter> {

    List<ChapterVO> getCourseChapterTrainingByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
