package com.lvyang.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvyang.edu_service.entity.EduTeacher;
import com.lvyang.edu_service.mapper.EduTeacherMapper;
import com.lvyang.edu_service.service.EduTeacherService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> frontPageTeacher) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("level");
        baseMapper.selectPage(frontPageTeacher, wrapper);
        List<EduTeacher> records = frontPageTeacher.getRecords();
        long current = frontPageTeacher.getCurrent();
        long pages = frontPageTeacher.getPages();
        long size = frontPageTeacher.getSize();
        long total = frontPageTeacher.getTotal();
        boolean hasNext = frontPageTeacher.hasNext();//是否有下一页
        boolean hasPrevious = frontPageTeacher.hasPrevious();//是否有上一页

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        if (current>pages) {
            return null;
        } else {
            return map;
        }

    }
}
