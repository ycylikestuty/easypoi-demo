package com.example.easypoidemo.entity.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ycy
 */
@Data
public class UserImportDTO implements Serializable, IExcelModel, IExcelDataModel {

    private static final long serialVersionUID = 7574913632780995687L;

    @Excel(name = "用户名")
    private String username;

    @Excel(name = "昵称")
    private String nickname;

    @Excel(name = "等级")
    private Long grade;

    @Excel(name = "状态", replace = {"正常_0", "禁言_1"})
    private Integer status;

    private Integer rowNum;

    @Excel(name = "信息")
    private String errorMsg;

    @Override
    public Integer getRowNum() {
        return this.rowNum;
    }

    @Override
    public void setRowNum(Integer integer) {
        this.rowNum = integer;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public void setErrorMsg(String s) {
        this.errorMsg = s;
    }
}
