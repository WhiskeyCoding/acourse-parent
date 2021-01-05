package com.lvyang.edu_service.entity.excel;

/**
 * @Author: lvyang
 * @Description:
 * @Date: 2020/12/8 0:36
 * @Version: 1.0
 */

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}

