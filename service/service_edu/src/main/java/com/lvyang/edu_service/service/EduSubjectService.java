package com.lvyang.edu_service.service;

import com.lvyang.edu_service.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lvyang.edu_service.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author lvyang
 * @since 2020-12-08
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}
