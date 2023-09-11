package edu.hnu.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.base.module.R;
import edu.hnu.product.mapper.InfoMapper;
import edu.hnu.product.module.dto.QueryProductParamsDto;
import edu.hnu.product.module.po.Info;
import edu.hnu.product.service.InfoService;
import io.swagger.models.auth.In;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
  RedisTemplate redisTemplate;

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

  @Override
  public Info getByRIC(String ric) {
    LambdaQueryWrapper<Info> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Info::getRic, ric);
    return infoMapper.selectOne(queryWrapper);
  }

  @Override
  public Info getByRICCache(String ric) {
    Object json = redisTemplate.opsForValue().get("ric:" + ric);
    if(json != null) {
      String jsonString = json.toString();
      Info info = JSON.parseObject(jsonString, Info.class);
      return info;
    } else {
      Info info = getByRIC(ric);
      if(info != null) {
        redisTemplate.opsForValue().set("ric:" + ric, JSON.toJSONString(info));
      }
      return info;
    }
  }

  @Override
  public Info getByTicker(String ticker) {
    LambdaQueryWrapper<Info> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Info::getTicker, ticker);
    return infoMapper.selectOne(queryWrapper);
  }

  @Override
  public Info getByIdCache(Integer id) {
    Object json = redisTemplate.opsForValue().get("product_id:" + id);
    if(json != null) {
      String jsonString = json.toString();
      Info info = JSON.parseObject(jsonString, Info.class);
      return info;
    } else {
      Info info = getById(id);
      if(info != null) {
        redisTemplate.opsForValue().set("product_id:" + id, JSON.toJSONString(info));
      }
      return info;
    }
  }

  @Override
  public Info getById(Integer id) {
    LambdaQueryWrapper<Info> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(Info::getId, id);
    return infoMapper.selectOne(lambdaQueryWrapper);
  }

  @Override
  public Info getByTickerCache(String ticker) {
    Object json = redisTemplate.opsForValue().get("ticker:" + ticker);
    if(json != null) {
      String jsonString = json.toString();
      Info info = JSON.parseObject(jsonString, Info.class);
      return info;
    } else {
      Info info = getByTicker(ticker);
      if(info != null) {
        redisTemplate.opsForValue().set("ticker:" + ticker, JSON.toJSONString(info));
      }
      return info;
    }
  }

  @Override
  public R add(String name, String ticker, String description, String issueSector, BigDecimal price,
      String ric) {
    Info info = new Info();
    info.setName(name);
    info.setTicker(ticker);
    info.setDescription(description);
    info.setIssueSector(issueSector);
    info.setPrice(price);
    info.setRic(ric);
    infoMapper.insert(info);
    return R.ok("添加成功", info);
  }

  @Override
  public PageResult<Info> queryInfoList(PageParams pageParams, QueryProductParamsDto queryProductParamsDto) {
    LambdaQueryWrapper<Info> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.like(StringUtils.isNotEmpty(queryProductParamsDto.getName()), Info::getName, queryProductParamsDto.getName());
    lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryProductParamsDto.getIssueSector()), Info::getIssueSector, queryProductParamsDto.getIssueSector());
    lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryProductParamsDto.getTicker()), Info::getTicker, queryProductParamsDto.getTicker());
    lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryProductParamsDto.getRic()), Info::getRic, queryProductParamsDto.getRic());
    Page<Info> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
    Page<Info> pageResult = infoMapper.selectPage(page, lambdaQueryWrapper);
    List<Info> items = pageResult.getRecords();
    long total = pageResult.getTotal();
    PageResult<Info> infoPageResult = new PageResult<>(items, total, pageParams.getPageNo(), pageParams.getPageSize());
    return infoPageResult;
  }

  @Override
  public Integer getInventory(Integer productId) {
    Info info = infoMapper.selectById(productId);
    return info.getStock();
  }

  @Override
  public void decreaseInventory(Integer productId, Integer size) {

    Info info = infoMapper.selectById(productId);
    info.setStock(info.getStock() - size);
    infoMapper.updateById(info);

  }

}
