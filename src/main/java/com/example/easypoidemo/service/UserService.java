package com.example.easypoidemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.easypoidemo.entity.UserDO;
import com.example.easypoidemo.entity.dto.UserExportDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ycy
 */
public interface UserService extends IService<UserDO> {
    String importExcel(MultipartFile file) throws Exception;

    List<UserExportDTO> getUserExportList();
}
