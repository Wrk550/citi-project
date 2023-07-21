package edu.hnu.ucenter.model.dto;

import lombok.Data;

/**
 * @author Mr.M
 * @version 1.0
 * @description 认证用户请求参数
 * @date 2022/9/29 10:56
 */
@Data
public class AuthParamsDto {

    private String username; //用户名
    private String password; //域  用于扩展
    private String authType; // 认证的类型   password:用户名密码模式类型    sms:短信模式类型


}
