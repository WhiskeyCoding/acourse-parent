package com.lvyang.edu_service.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.edu_service.entity.EduTeacher;
import com.lvyang.edu_service.entity.vo.TeacherQuery;
import com.lvyang.edu_service.service.EduTeacherService;
import com.lvyang.service_base.exceptionhandler.CreateTeacherInfoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lvyang
 * @since 2020-11-25
 */
@Api(tags = {"讲师管理"})
@RestController
@RequestMapping("/edu_service/teacher")
@CrossOrigin
public class EduTeacherController {

    /**
     * EduTeacherService
     * service 注入
     */
    @Autowired
    private EduTeacherService teacherService;

    /**
     * 1 查询讲师表所有数据(rest风格）
     * @return
     */
    @ApiOperation(value = "查阅所有讲师资料")
    @GetMapping("retrieveAllTeacherData")
    public JsonResultUnity retrieveAllTeacherData() {
        //调用service的方法实现查询所有操作
        List<EduTeacher> list = teacherService.list(null);
        return JsonResultUnity.correct().data("allTeacherData", list);
    }

    /**
     * 2逻辑删除讲师的方法
     *
     * @param id 讲师Id
     * @return
     */
    @ApiOperation(value = "根据ID逻辑删除讲师")
    @DeleteMapping("deleteTeacherLogically/{id}")
    public JsonResultUnity deleteTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        EduTeacher teacher = teacherService.getById(id);
        if (teacher.getIsDeleted() == false) {
            teacherService.removeById(id);
            return JsonResultUnity.correct();
        } else {
            return JsonResultUnity.error();
        }
    }


    /**
     * 3分页查询讲师的方法
     *
     * @param current 当前页
     * @param limit   每页显示的条数
     * @return
     */
    @GetMapping("pagedQueryTeacher/{current}/{limit}")
    public JsonResultUnity pageQueryTeacher(
            @PathVariable long current,
            @PathVariable long limit
    ) {
        Page<EduTeacher> pageQueryTeacher = new Page<>(current, limit);
        teacherService.page(pageQueryTeacher, null);
        long total = pageQueryTeacher.getTotal();
        List<EduTeacher> records = pageQueryTeacher.getRecords();
        return JsonResultUnity.correct().data("total", total).data("rows", records);
    }


    /**
     * 4条件查询带分页方法
     *
     * @param current      当前页
     * @param limit        每页显示的条数
     * @param teacherQuery 讲师搜索请求
     * @return
     */
    @PostMapping("pageQueryTeacherCondition/{current}/{limit}")
    public JsonResultUnity pageQueryTeacherCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherQuery) {
        //创建page对象
        Page<EduTeacher> pageQueryTeacher = new Page<>(current, limit);
        //条件构建
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        Integer sort = teacherQuery.getSort();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!(name == null || "".equals(name))) {
            wrapper.like("name", name);
        }
        if (sort != null) {
            wrapper.eq("sort", sort);
        }
        if (level != null) {
            wrapper.eq("level", level);
        }
        if (!(begin == null || "".equals(begin))) {
            wrapper.ge("gmt_create", begin);
        }
        if (!(end == null || "".equals(end))) {
            wrapper.le("gmt_create", end);
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件查询分页
        teacherService.page(pageQueryTeacher, wrapper);
        long total = pageQueryTeacher.getTotal();
        //数据list集合
        List<EduTeacher> records = pageQueryTeacher.getRecords();
        return JsonResultUnity.correct().data("total", total).data("rows", records);
    }

    /**
     * 5 接口：添加讲师
     *
     * @param eduTeacher 讲师对象
     * @return
     */
    @PostMapping("createTeacher")
    public JsonResultUnity createTeacher(@RequestBody EduTeacher eduTeacher) {
        try{
            boolean save = teacherService.save(eduTeacher);
            if (save) {
                return JsonResultUnity.correct();
            } else {
                return JsonResultUnity.error();
            }
        }
        catch (Exception e){
            throw new CreateTeacherInfoException(20001,"添加失败,请重新添加");
        }
    }


    /**
     * 7 根据讲师ID查询讲师信息
     *
     * @param id
     * @return
     */
    @GetMapping("getTeacher/{id}")
    public JsonResultUnity getById(@PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);
        if (eduTeacher.getIsDeleted() == false) {
            return JsonResultUnity.correct().data("teacherItem", eduTeacher);
        } else {
            return JsonResultUnity.error();
        }

    }

    /**
     * 6 讲师修改功能
     *
     * @param eduTeacher
     * @return
     */
    @PostMapping("updateTeacher")
    public JsonResultUnity updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        if (flag) {
            return JsonResultUnity.correct();
        } else {
            return JsonResultUnity.error();
        }
    }
}


