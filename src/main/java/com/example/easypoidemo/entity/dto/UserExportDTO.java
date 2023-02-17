package com.example.easypoidemo.entity.dto;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ycy
 */
@Data
public class UserExportDTO implements Serializable {
    private static final long serialVersionUID = -4793037278754388317L;
    @Excel(name = "昵称")
    private String nickname;

    @Excel(name = "等级")
    private Long grade;

    @Excel(name = "状态", replace = {"正常_0", "禁言_1"})
    private Integer status;
}
