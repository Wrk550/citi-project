package edu.hnu.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hnu.base.module.R;
import edu.hnu.client.mapper.OwnMapper;
import edu.hnu.client.model.po.Info;
import edu.hnu.client.model.po.Own;
import edu.hnu.client.service.OwnService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wrk
 */
@Slf4j
@Service
public class OwnServiceImpl extends ServiceImpl<OwnMapper, Own> implements OwnService {

  @Autowired
  OwnMapper ownMapper;

  @Override
  public boolean getById(Integer clientId) {
    LambdaQueryWrapper<Own> queryWrapper = new LambdaQueryWrapper();
    queryWrapper.eq(Own::getClientId, clientId);
    List<Own> ownList = ownMapper.selectList(queryWrapper);
    if (ownList.size() != 0) {
      return true;
    }
    return false;
  }
}
