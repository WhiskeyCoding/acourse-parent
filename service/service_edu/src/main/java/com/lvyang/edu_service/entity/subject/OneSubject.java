package com.lvyang.edu_service.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/10 9:53
 * @Version: 1.0
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    //能存多个二级数据的集合（集合）
    private List<TwoSubject> children = new ArrayList<>();
}
