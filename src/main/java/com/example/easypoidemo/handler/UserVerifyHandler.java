package com.example.easypoidemo.handler;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.easypoidemo.entity.UserDO;
import com.example.easypoidemo.entity.dto.UserImportDTO;
import com.example.easypoidemo.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author ycy
 * 自定义校验规则
 */
@Component
public class UserVerifyHandler implements IExcelVerifyHandler<UserImportDTO> {
    @Resource
    private UserService userService;

    @Override
    public ExcelVerifyHandlerResult verifyHandler(UserImportDTO userImportDTO) {
        ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult();
        //新增用户，如果用户的username存在则校验不通过
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<UserDO>()
                .eq(UserDO::getUsername, userImportDTO.getUsername());
        UserDO userDO = userService.getOne(queryWrapper);
        if (Objects.isNull(userDO)) {
            result.setSuccess(true);
            return result;
        }
        result.setMsg("校验失败");
        result.setSuccess(false);
        return result;
    }
}
