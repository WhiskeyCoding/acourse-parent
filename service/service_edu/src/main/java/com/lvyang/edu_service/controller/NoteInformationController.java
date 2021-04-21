package com.lvyang.edu_service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvyang.common_utils.JsonResultUnity;
import com.lvyang.edu_service.entity.NoteInformation;
import com.lvyang.edu_service.entity.vo.NoteListShowVO;
import com.lvyang.edu_service.nacosclient.PortalClient;
import com.lvyang.edu_service.service.NoteInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lvyang
 * @since 2021-04-17
 */
@RestController
@RequestMapping("/edu_service/note_information")
@Api(tags = {"11-Note Management"})
public class NoteInformationController {
    @Autowired
    private NoteInformationService noteInformationService;
    @Autowired
    private PortalClient portalClient;


    //分页查询所有笔记信息
    @ApiOperation(value = "分页查询所有笔记列表")
    @PostMapping("pageNoteInformation/{page}/{limit}")
    public JsonResultUnity pageNoteInformation(@PathVariable long page, @PathVariable long limit) {
        Page<NoteInformation> noteInformationPage = new Page<>(page, limit);
        Map<String,Object> pageNoteMap = noteInformationService.getNoteFrontList(noteInformationPage);
        if(pageNoteMap != null){
            return JsonResultUnity.correct().data(pageNoteMap);
        }else{
            return JsonResultUnity.error().message("最后一页了。");
        }
    }

    //根据NoteId查询笔记信息
    @ApiOperation(value = "据NoteId查询笔记信息")
    @GetMapping("getNoteFrontInfoByNoteId/{noteId}")
    public JsonResultUnity getNoteFrontInfoByNoteId(@PathVariable String noteId) {
        NoteInformation noteInfo = noteInformationService.getById(noteId);
        NoteListShowVO noteListShowVO = new NoteListShowVO();
        BeanUtils.copyProperties(noteInfo,noteListShowVO);
        return JsonResultUnity.correct().data("noteInfo", noteListShowVO);
    }

    //根据用户ID查询其名下所有笔记信息
    @ApiOperation(value = "根据用户ID查询其名下所有笔记信息")
    @GetMapping("pageQueryNote/{current}/{limit}")
    public JsonResultUnity pageQueryNote(@PathVariable Long current, @PathVariable Long limit, @RequestParam(required = false) String memberId) {
        Page<NoteInformation> pageParam = new Page<>(current, limit);
        QueryWrapper<NoteInformation> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id", memberId);
        noteInformationService.page(pageParam, wrapper);
        List<NoteInformation> noteList = pageParam.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("notes", noteList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());
        return JsonResultUnity.correct().data(map);
    }
}

