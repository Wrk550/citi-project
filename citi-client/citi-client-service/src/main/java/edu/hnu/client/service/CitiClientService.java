package edu.hnu.client.service;

import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.client.module.dto.QueryClientParamsDto;
import edu.hnu.client.module.po.UserInfoEntity;

public interface CitiClientService {
  public PageResult<UserInfoEntity> list(Integer auth, PageParams pageParams);
}
