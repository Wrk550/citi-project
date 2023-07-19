package edu.hnu.client.api;

import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.client.module.dto.QueryClientParamsDto;
import edu.hnu.client.module.po.UserInfoEntity;
import edu.hnu.client.service.CitiClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户信息管理接口",tags = "用户信息管理接口")
@RestController
public class CitiClientController {

  @Autowired
  CitiClientService citiClientService;

  @GetMapping("/client/test")
  public String test() {
    return "test";

  }

  @ApiOperation("用户信息查询接口")
  @PostMapping("/client/list")
  public PageResult<UserInfoEntity> list(Integer auth, PageParams pageParams) {
    return citiClientService.list(auth, pageParams);
  }

}
