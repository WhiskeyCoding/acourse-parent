package com.lvyang.edu_service.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvyang.edu_service.Listener.SubjectExcelListener;
import com.lvyang.edu_service.entity.EduSubject;
import com.lvyang.edu_service.entity.excel.SubjectData;
import com.lvyang.edu_service.entity.subject.OneSubject;
import com.lvyang.edu_service.entity.subject.TwoSubject;
import com.lvyang.edu_service.mapper.EduSubjectMapper;
import com.lvyang.edu_service.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author lvyang
 * @since 2020-12-08
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //课程分类列表（树形）
    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //查询一级分类 parentid=0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
        //查询二级分类 parentid！=0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //创建list集合，用于存储最终封装的数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //封装一级分类

        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject eduSubject1 = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
            // oneSubject.setId(eduSubject.getId());
            // oneSubject.setTitle(eduSubject.getTitle());
            BeanUtils.copyProperties(eduSubject1,oneSubject);
            finalSubjectList.add(oneSubject);
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (int j = 0; j < twoSubjectList.size(); j++) {
                EduSubject eduSubject2 = twoSubjectList.get(j);

                if(eduSubject2.getParentId().equals(eduSubject1.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubject2,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubjectList);
        }
        //封装二级分类
        

        return finalSubjectList;
    }
}
