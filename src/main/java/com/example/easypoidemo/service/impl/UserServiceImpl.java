package com.example.easypoidemo.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.easypoidemo.entity.UserDO;
import com.example.easypoidemo.entity.dto.UserExportDTO;
import com.example.easypoidemo.entity.dto.UserImportDTO;
import com.example.easypoidemo.mapper.UserMapper;
import com.example.easypoidemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ycy
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
//    @Resource
//    private UserVerifyHandler userVerifyHandler;

    @Override
    public List<UserExportDTO> getUserExportList() {
        List<UserDO> userDOList = this.list();
        //将数据库中存储的UserDO对象转为UserExportDTO
        List<UserExportDTO> exportDTOList = userDOList.stream().map(
                userDO -> {
                    UserExportDTO userExportDTO = new UserExportDTO();
                    BeanUtils.copyProperties(userDO, userExportDTO);
                    return userExportDTO;
                }).collect(Collectors.toList());
        return exportDTOList;
    }

    @Override
    public String importExcel(MultipartFile file) throws Exception {
        ImportParams importParams = new ImportParams();
        //excel标题所处的行数，默认为0
        importParams.setTitleRows(1);
        //是否需要校验上传的excel
        importParams.setNeedVerify(false);
        //使用自定义的验证器
//        importParams.setVerifyHandler(userVerifyHandler);
        //进行具体的数据校验
        ExcelImportResult<UserImportDTO> result = ExcelImportUtil.importExcelMore(file.getInputStream(), UserImportDTO.class, importParams);
        if (!result.isVerifyFail() && !CollectionUtils.isEmpty(result.getList())) {
            for (UserImportDTO userImportDTO : result.getList()) {
                log.info("从Excel导入数据到数据库的详细为 ：{}", userImportDTO);
                UserDO userDO = new UserDO();
                BeanUtils.copyProperties(userImportDTO, userDO);
                this.save(userDO);
            }
        } else {
            for (UserImportDTO userImportDTO : result.getList()) {
                log.info("校验失败的详细为 ：{}", userImportDTO);
            }
            return "校验失败";
        }
        return "导入成功";
    }
}
