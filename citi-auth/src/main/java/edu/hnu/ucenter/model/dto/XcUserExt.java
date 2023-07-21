package edu.hnu.ucenter.model.dto;

import edu.hnu.ucenter.model.po.XcUser;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @description 用户扩展信息
 * @author Mr.M
 * @date 2022/9/30 13:56
 * @version 1.0
 */
@Data
public class XcUserExt extends XcUser {
    //用户权限
    List<String> permissions = new ArrayList<>();
}
