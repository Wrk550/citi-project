package edu.hnu.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.base.module.R;
import edu.hnu.client.mapper.OwnMapper;
import edu.hnu.client.model.dto.QueryOwnParamsDto;
import edu.hnu.client.model.po.Own;
import edu.hnu.client.service.OwnService;
import edu.hnu.product.module.po.Info;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

  @Override
  public Integer getByProductIdAndClientId(Integer productId, Integer clientId) {
    LambdaQueryWrapper<Own> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(Own::getClientId, clientId);
    lambdaQueryWrapper.eq(Own::getProductId, productId);
    Own own = ownMapper.selectOne(lambdaQueryWrapper);
    if (own == null) {
      return 0;
    } else {
      return own.getSize();
    }
  }

  @Override
  public void updateByProductIdAndClientId(Integer productId, Integer clientId, Integer size) {
    LambdaQueryWrapper<Own> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(Own::getClientId, clientId);
    lambdaQueryWrapper.eq(Own::getProductId, productId);
    Own own = ownMapper.selectOne(lambdaQueryWrapper);
    own.setSize(own.getSize() - size);
    ownMapper.updateById(own);
  }

  @Override
  public PageResult<Own> queryInfoList(PageParams pageParams, QueryOwnParamsDto queryOwnParamsDto) {
    LambdaQueryWrapper<Own> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.like(StringUtils.isNotEmpty(queryOwnParamsDto.getClientName()), Own::getCilentName, queryOwnParamsDto.getClientName());
    lambdaQueryWrapper.like(StringUtils.isNotEmpty(queryOwnParamsDto.getProductName()), Own::getProductName, queryOwnParamsDto.getProductName());
    lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryOwnParamsDto.getRic()), Own::getRic, queryOwnParamsDto.getRic());
    lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryOwnParamsDto.getTicker()), Own::getTicker, queryOwnParamsDto.getTicker());
    Page<Own> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
    Page<Own> pageResult = ownMapper.selectPage(page, lambdaQueryWrapper);
    List<Own> ownList = pageResult.getRecords();
    long total = pageResult.getTotal();
    PageResult<Own> ownPageResult = new PageResult<>(ownList, total, pageParams.getPageNo(), pageParams.getPageSize());
    return ownPageResult;
  }


}
