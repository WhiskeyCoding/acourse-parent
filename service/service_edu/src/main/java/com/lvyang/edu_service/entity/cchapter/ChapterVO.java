package com.lvyang.edu_service.entity.cchapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/11 20:03
 * @Version: 1.0
 */
@Data
public class ChapterVO {
    private String id;
    private String title;
    private List<TrainingVO> children = new ArrayList<>();
}
