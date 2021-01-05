package com.lvyang.edu_service.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lvyang.edu_service.entity.EduSubject;
import com.lvyang.edu_service.entity.excel.SubjectData;
import com.lvyang.edu_service.service.EduSubjectService;
import com.lvyang.service_base.exceptionhandler.ACourseException;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/6 10:39
 * @Version: 1.0
 */

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    /**
     * SubjectExcelListener 不能交给Spring进行管理，需要自己new，不能注入其他对象
     * 不能实现数据库操作
     * @param subjectData
     * @param analysisContext
     */

    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    final String SID = "0";
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw new ACourseException(20001, "文件数据为空");
        }
        //一行一行读，每次读取有两个值，第一个值一级分类，第二个值二级分类。
        //判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName(),SID);
        if (existOneSubject == null) {
            //一级分类不存在，添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId(SID);
            existOneSubject.setTitle(subjectData.getOneSubjectName());//一级分类名称
            subjectService.save(existOneSubject);
        }
        /**
         * 获取PID
         */
        String PID = existOneSubject.getId();
        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(),PID);
        if (existTwoSubject==null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(PID);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//二级分类名称
            subjectService.save(existTwoSubject);
        }
    }


    /**
     * 判断一级分类、二级分类不能重复添加
     */
    private EduSubject existOneSubject(EduSubjectService subjectService,String oneSubjectName,String SID) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", oneSubjectName);
        wrapper.eq("parent_id", SID);
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    private EduSubject existTwoSubject(EduSubjectService subjectService,String TwoSubjectName,String PID) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", TwoSubjectName);
        wrapper.eq("parent_id", PID);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }



    /**
     *
     * @param analysisContext
     */

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
