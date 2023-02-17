package com.example.easypoidemo.controller;

import com.example.easypoidemo.entity.dto.UserExportDTO;
import com.example.easypoidemo.service.UserService;
import com.example.easypoidemo.util.ExcelUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ycy
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(path = "/importExcel")
    public String importExcel(@RequestParam("file") MultipartFile file) {
        try {
            String result = userService.importExcel(file);
            return result;

        } catch (Exception e) {
            return "导入失败";
        }
    }

    @GetMapping(path = "/exportExcel")
    public void export(HttpServletResponse response) {
        List<UserExportDTO> userExportDTOList = userService.getUserExportList();
        ExcelUtil.exportExcel(userExportDTOList, "用户数据", "sheet1", UserExportDTO.class, "测试导出表.xlsx", response);
    }
}
