package com.lvyang.edu_service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lvyang.edu_service.entity.EduTeacher;
import org.springframework.web.multipart.MultipartFile;

/**
 * 讲师 服务类
 *
 * @author lvyang
 * @since 2020-11-25
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void saveSubject(MultipartFile file);

}
