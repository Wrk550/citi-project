package edu.hnu.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hnu.base.module.R;
import edu.hnu.client.mapper.InfoMapper;
import edu.hnu.client.model.po.Info;
import edu.hnu.client.model.po.Own;
import edu.hnu.client.service.InfoService;
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
public class InfoServiceImpl extends ServiceImpl<InfoMapper, Info> implements InfoService {

  @Autowired
  InfoMapper infoMapper;

  @Autowired
  OwnService ownService;

  @Override
  public R addClient(String clientName, String phoneNumber) {
    LambdaQueryWrapper<Info> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Info::getPhoneNumber, phoneNumber);
    List<Info> infoList = infoMapper.selectList(queryWrapper);
    if (infoList.size() != 0) {
      return R.error("此手机号已被绑定");
    }
    Info info = new Info();
    info.setClientName(clientName);
    info.setPhoneNumber(phoneNumber);
    infoMapper.insert(info);
    return R.ok("添加成功", info);
  }
}
