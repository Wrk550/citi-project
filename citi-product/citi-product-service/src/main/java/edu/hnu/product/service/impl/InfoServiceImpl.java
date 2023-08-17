package edu.hnu.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.product.mapper.InfoMapper;
import edu.hnu.product.module.po.Info;
import edu.hnu.product.service.InfoService;
import java.util.Collection;
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
public class InfoServiceImpl implements InfoService {

  @Autowired
  InfoMapper infoMapper;

  @Override
  public PageResult<Info> list(PageParams pageParams) {
    LambdaQueryWrapper<Info> queryWrapper = new LambdaQueryWrapper<>();
    Page<Info> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
    Page<Info> result = infoMapper.selectPage(page, queryWrapper);
    List<Info> list = result.getRecords();

    //测试
    System.out.println(list);

    long total = result.getTotal();
    PageResult<Info> pageResult = new PageResult<>(list, total, pageParams.getPageNo(),
        pageParams.getPageSize());
    return pageResult;
  }

}
