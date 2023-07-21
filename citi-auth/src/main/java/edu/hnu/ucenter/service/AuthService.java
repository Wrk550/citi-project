package edu.hnu.ucenter.service;

import edu.hnu.ucenter.model.dto.AuthParamsDto;
import edu.hnu.ucenter.model.dto.XcUserExt;

/**
 * @author Mr.M
 * @version 1.0
 * @description 统一的认证接口
 * @date 2023/2/24 11:55
 */
public interface AuthService {

 /**
  * @description 认证方法
  * @param authParamsDto 认证参数
  * @return edu.hnu.ucenter.model.po.XcUser 用户信息
  * @author Mr.M
  * @date 2022/9/29 12:11
  */
 XcUserExt execute(AuthParamsDto authParamsDto);

}