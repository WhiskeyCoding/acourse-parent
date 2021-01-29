package com.lvyang.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvyang.edu_service.entity.EduCourseDescription;
import com.lvyang.edu_service.entity.EduCourseInfo;
import com.lvyang.edu_service.entity.vo.CourseFrontShowVO;
import com.lvyang.edu_service.entity.vo.CourseFrontVO;
import com.lvyang.edu_service.entity.vo.CourseInfoVO;
import com.lvyang.edu_service.entity.vo.CoursePublishVO;
import com.lvyang.edu_service.mapper.EduCourseInfoMapper;
import com.lvyang.edu_service.service.EduCourseChapterService;
import com.lvyang.edu_service.service.EduCourseDescriptionService;
import com.lvyang.edu_service.service.EduCourseInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvyang.edu_service.service.EduCourseTrainingService;
import com.lvyang.service_base.exceptionhandler.ACourseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程基本信息 服务实现类
 * </p>
 *
 * @author lvyang
 * @since 2020-12-10
 */
@Service
public class EduCourseInfoServiceImpl extends ServiceImpl<EduCourseInfoMapper, EduCourseInfo> implements EduCourseInfoService {

    //课程描述注入
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    //注入小节、章节的
    @Autowired
    private EduCourseChapterService eduCourseChapterService;
    @Autowired
    private EduCourseTrainingService eduCourseTrainingService;

    //添加课程基本信息方法实现
    @Override
    public String saveCourseInfo(CourseInfoVO courseInfoVO) {
        //1 向课程表添加基本信息
        //CourseInfoVO->转换成EduCourseInfo
        EduCourseInfo eduCourseInfo = new EduCourseInfo();
        BeanUtils.copyProperties(courseInfoVO, eduCourseInfo);
        int insert = baseMapper.insert(eduCourseInfo);//影响行数
        eduCourseInfo.setIsDeleted(0);
        if (insert == 0) {
            //添加失败
            throw new ACourseException(20001, "添加课程信息失败");
        }
        //获取课程ID
        String CID = eduCourseInfo.getId();
        //2 向课程简介表添加课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(CID);
        eduCourseDescription.setDescription(courseInfoVO.getDescription());
        eduCourseDescriptionService.save(eduCourseDescription);
        return CID;
    }

    /**
     * 根据课程ID查询课程基本信息
     */
    @Override
    public CourseInfoVO getCourseInfo(String courseId) {
        //1查询课程表
        EduCourseInfo eduCourseInfo = baseMapper.selectById(courseId);
        CourseInfoVO courseInfoVO = new CourseInfoVO();
        BeanUtils.copyProperties(eduCourseInfo, courseInfoVO);
        //2查询描述表
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        String description = eduCourseDescription.getDescription();
        courseInfoVO.setDescription(description);
        return courseInfoVO;
    }

    /**
     * 修改课程信息
     */
    @Override
    public void updateCourseInfo(CourseInfoVO courseInfoVO) {
        //1修改课程表
        EduCourseInfo eduCourseInfo = new EduCourseInfo();
        BeanUtils.copyProperties(courseInfoVO, eduCourseInfo);
        int update = baseMapper.updateById(eduCourseInfo);
        if (update == 0) {
            throw new ACourseException(20001, "修改课程信息失败");
        }
        //2修改描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVO.getId());
        eduCourseDescription.setDescription(courseInfoVO.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);

    }

    /**
     * 获取publish course信息
     */
    @Override
    public CoursePublishVO getpublishCourseInfo(String courseId) {
        //调用自己写的Mapper
        CoursePublishVO publishCourseInfo = baseMapper.getPublishCourseInfo(courseId);
        return publishCourseInfo;
    }

    @Override
    public void removeCourse(String courseId) {
        //1.根据课程ID删除小节
        eduCourseTrainingService.removeTrainingByCourseId(courseId);
        //2.根据课程ID删除章节
        eduCourseChapterService.removeChapterByCourseId(courseId);
        //3.根据课程ID删除描述
        eduCourseDescriptionService.removeById(courseId);
        //4.根据课程ID删除课程本身
        int result = baseMapper.deleteById(courseId);
        if (result == 0) {
            throw new ACourseException(20001, "删除失败");
        }
    }

    @Override
    public Map<String, Object> getFrontCoursePageList(Page<EduCourseInfo> pageParam, CourseFrontVO courseFrontVO) {
        QueryWrapper<EduCourseInfo> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseFrontVO.getSubjectParentId())) {
            wrapper.eq("subject_parent_id", courseFrontVO.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseFrontVO.getSubjectId())) {
            wrapper.eq("subject_id", courseFrontVO.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseFrontVO.getBuyCountSort())) {
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVO.getGmtCreateSort())) {
            wrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseFrontVO.getPriceSort())) {
            wrapper.orderByDesc("price");
        }
        baseMapper.selectPage(pageParam, wrapper);
        List<EduCourseInfo> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();
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

    @Override
    public CourseFrontShowVO getCourseBaseInfo(String courseId) {
        CourseFrontShowVO courseBaseInfo = baseMapper.getCourseBaseInfo(courseId);
        return courseBaseInfo;
    }

}
