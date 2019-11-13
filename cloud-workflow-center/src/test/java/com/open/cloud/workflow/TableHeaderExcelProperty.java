package com.open.cloud.workflow;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TableHeaderExcelProperty extends BaseRowModel {

    /**
     * value: 表头名称
     * index: 列的号, 0表示第一列
     */
    @ExcelProperty(value = "明文密码", index = 0)
    private String password1;

    @ExcelProperty(value = "密文密码", index = 1)
    private String password2;

}
