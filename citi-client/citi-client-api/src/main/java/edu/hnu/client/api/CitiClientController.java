package edu.hnu.client.api;

import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.client.module.po.UserInfoEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CitiClientController {
  @RequestMapping("/client/list")
  public PageResult<UserInfoEntity> list(PageParams pageParams) {
    return null;
  }

}
