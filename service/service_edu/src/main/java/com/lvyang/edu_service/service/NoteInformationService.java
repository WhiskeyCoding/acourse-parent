package com.lvyang.edu_service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvyang.edu_service.entity.NoteInformation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lvyang
 * @since 2021-04-17
 */
public interface NoteInformationService extends IService<NoteInformation> {

    Map<String, Object> getNoteFrontList(Page<NoteInformation> noteInformationPage);
}
