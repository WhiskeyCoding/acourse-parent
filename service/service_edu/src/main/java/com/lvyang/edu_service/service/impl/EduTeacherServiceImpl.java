package com.lvyang.edu_service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvyang.edu_service.entity.EduTeacher;
import com.lvyang.edu_service.mapper.EduTeacherMapper;
import com.lvyang.edu_service.service.EduTeacherService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-25
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void saveSubject(MultipartFile file) {

    }
}
