package com.lvyang.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvyang.edu_service.entity.NoteInformation;
import com.lvyang.edu_service.mapper.NoteInformationMapper;
import com.lvyang.edu_service.service.NoteInformationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lvyang
 * @since 2021-04-17
 */
@Service
public class NoteInformationServiceImpl extends ServiceImpl<NoteInformationMapper, NoteInformation> implements NoteInformationService {

    @Override
    public Map<String, Object> getNoteFrontList(Page<NoteInformation> noteInformationPage) {
        QueryWrapper<NoteInformation> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_modified");
        baseMapper.selectPage(noteInformationPage, wrapper);
        List<NoteInformation> records = noteInformationPage.getRecords();
        long current = noteInformationPage.getCurrent();
        long pages = noteInformationPage.getPages();
        long size = noteInformationPage.getSize();
        long total = noteInformationPage.getTotal();
        boolean hasNext = noteInformationPage.hasNext();
        boolean hasPrevious = noteInformationPage.hasPrevious();
        Map<String,Object> map = new HashMap<>();
        map.put("rows", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        if(current>pages){
            return null;
        }else{
            return map;
        }
    }
}
