package edu.hnu.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.client.mapper.UserInfoDao;
import edu.hnu.client.module.dto.QueryClientParamsDto;
import edu.hnu.client.module.po.UserInfoEntity;
import edu.hnu.client.service.CitiClientService;
import groovy.util.logging.Slf4j;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CitiClientServiceImpl implements CitiClientService {

  @Autowired
  UserInfoDao userInfoDao;

  @Override
  public PageResult<UserInfoEntity> list(Integer auth, PageParams pageParams) {
    LambdaQueryWrapper<UserInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(UserInfoEntity::getAuth, auth);
    Page<UserInfoEntity> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
    Page<UserInfoEntity> result = userInfoDao.selectPage(page, queryWrapper);
    List<UserInfoEntity> list = result.getRecords();

    //测试
    System.out.println(list);

    long total = result.getTotal();
    PageResult<UserInfoEntity> pageResult = new PageResult<>(list, total, pageParams.getPageNo(),
        pageParams.getPageSize());
    return pageResult;
  }
}
