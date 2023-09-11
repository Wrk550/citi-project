package edu.hnu.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.base.module.R;
import edu.hnu.trade.feignclient.ClientServiceClient;
import edu.hnu.trade.feignclient.ProductServiceClient;
import edu.hnu.trade.mapper.InfoMapper;
import edu.hnu.trade.model.dto.QueryTradeParamsDto;
import edu.hnu.trade.model.po.Info;
import edu.hnu.trade.service.InfoService;
import java.math.BigDecimal;
import java.net.Socket;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
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
  ProductServiceClient productServiceClient;

  @Autowired
  ClientServiceClient clientServiceClient;

  @Autowired
  RedissonClient redissonClient;

  @Override
  public R sell(Integer clientId, String ticker, String ric, Integer size, String currency,
      Integer htPt) {
    if (ric.equals("") && ticker.equals("")) {
      return R.error("请输入RIC或股票代码以确认将要进行操作的股票");
    }
    Info info = new Info();
    if (clientServiceClient.getById(clientId).size() == 0) {
      return R.error(clientServiceClient.getById(clientId).toString());
    }
    info.setClientId(String.valueOf(clientId));
    info.setClientSide("sell");
    info.setDate(LocalDateTime.now().toLocalDate());
    edu.hnu.product.module.po.Info productByRic = new edu.hnu.product.module.po.Info();
    edu.hnu.product.module.po.Info productByTicker = new edu.hnu.product.module.po.Info();
    edu.hnu.product.module.po.Info product = new edu.hnu.product.module.po.Info();
    if (!ric.equals("")) {
      productByRic = productServiceClient.getByRIC(ric);
      if (productByRic == null) {
        return R.error("未找到该RIC代码对应的股票");
      }
      product = productByRic;
    }
    if (!ticker.equals("")) {
      productByTicker = productServiceClient.getByTicker(ticker);
      if (productByTicker == null) {
        return R.error("未找到该Ticker代码对应的股票");
      }
      product = productByTicker;
    }

    if (!ticker.equals("") && !ric.equals("") && !productByRic.getTicker()
        .equals(productByTicker.getTicker())) {
      return R.error("请输入同一只股票的RIC以及Ticker代码");
    }
    if (clientServiceClient.getSize(clientId, product.getId()) < size) {
      return R.error("Id为" + clientId + "的客户持有的ticker为" + product.getTicker() + "的股票数量不足，出售失败");
    }
    info.setRic(product.getRic());
    info.setTicker(product.getTicker());
    info.setSize(size);
    info.setPrice(product.getPrice());
    info.setNotionalUsd(new BigDecimal(size.toString()).multiply(product.getPrice()));
    info.setCurrency(currency);
    info.setIssueSector(product.getIssueSector());
    //后续加入鉴权后修改
    info.setSalesperson("user");

    if (htPt == 0) {
      info.setHtPt("HT");
    } else {
      info.setHtPt("PT");
    }
    infoMapper.insert(info);
    clientServiceClient.updateSize(clientId, product.getId(), size, 0);
    return R.ok("卖出成功", info);
  }

  @Override
  public R buy(Integer clientId, String ticker, String ric, Integer size, String currency,
      Integer htPt) {
    if (ric.equals("") && ticker.equals("")) {
      return R.error("请输入RIC或股票代码以确认将要进行操作的股票");
    }
    Info info = new Info();
    if (clientServiceClient.getById(clientId).size() == 0) {
      return R.error("未找到对应用户");
    }
    info.setClientId(String.valueOf(clientId));
    info.setClientSide("buy");
    info.setDate(LocalDateTime.now().toLocalDate());
    edu.hnu.product.module.po.Info productByRic = new edu.hnu.product.module.po.Info();
    edu.hnu.product.module.po.Info productByTicker = new edu.hnu.product.module.po.Info();
    edu.hnu.product.module.po.Info product = new edu.hnu.product.module.po.Info();
    if (!ric.equals("")) {
      productByRic = productServiceClient.getByRIC(ric);
      if (productByRic == null) {
        return R.error("未找到该RIC代码对应的股票");
      }
      product = productByRic;
    }
    if (!ticker.equals("")) {
      productByTicker = productServiceClient.getByTicker(ticker);
      if (productByTicker == null) {
        return R.error("未找到该Ticker代码对应的股票");
      }
      product = productByTicker;
    }

    if (!ticker.equals("") && !ric.equals("") && !productByRic.getTicker()
        .equals(productByTicker.getTicker())) {
      return R.error("请输入同一只股票的RIC以及Ticker代码");
    }

    //获取分布式锁
    RLock lock = redissonClient.getLock("Id" + product.getId());
    try {
      if (lock.tryLock(1, TimeUnit.SECONDS)) {
        if (productServiceClient.getInventory(product.getId()) >= size) {
          productServiceClient.updateInventory(product.getId(), size);
        } else {
          return R.error("库存不足");
        }
        info.setRic(product.getRic());
        info.setTicker(product.getTicker());
        info.setSize(size);
        info.setPrice(product.getPrice());
        info.setNotionalUsd(new BigDecimal(size.toString()).multiply(product.getPrice()));
        info.setCurrency(currency);
        info.setIssueSector(product.getIssueSector());
        info.setSalesperson("user");

        if (htPt == 0) {
          info.setHtPt("HT");
        } else {
          info.setHtPt("PT");
        }
        infoMapper.insert(info);
        clientServiceClient.updateSize(Integer.parseInt(String.valueOf(clientId)), product.getId(), size, 1);
        return R.ok("买入成功", info);
      } else {
        return R.error("请稍后重试");
      }
    } catch (Exception e) {
      return R.error("错误：" + e);
    } finally {
      //手动释放锁
      if (lock.isLocked() && lock.isHeldByCurrentThread()) {
        lock.unlock();
      }
    }
  }

  @Override
  public PageResult<Info> queryInfoList(PageParams pageParams,
      QueryTradeParamsDto queryTradeParamsDto) {
    LambdaQueryWrapper<Info> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(
        StringUtils.isNotEmpty(queryTradeParamsDto.getClientId()), Info::getClientId,
        queryTradeParamsDto.getClientId());
    lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryTradeParamsDto.getClientSide()),
        Info::getClientSide,
        queryTradeParamsDto.getClientSide());
    lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryTradeParamsDto.getIssueSector()),
        Info::getIssueSector, queryTradeParamsDto.getIssueSector());
    lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryTradeParamsDto.getTicker()),
        Info::getTicker, queryTradeParamsDto.getTicker());
    lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryTradeParamsDto.getRic()), Info::getRic,
        queryTradeParamsDto.getRic());
    lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryTradeParamsDto.getCurrency()),
        Info::getCurrency,
        queryTradeParamsDto.getCurrency());
    lambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryTradeParamsDto.getHtPt()), Info::getHtPt,
        queryTradeParamsDto.getHtPt());

    LocalDateTime targetTime = getDuration(queryTradeParamsDto.getTimePeriod());
    targetTime = targetTime.withHour(0).withMinute(0).withSecond(0).withNano(0);
    lambdaQueryWrapper.between(Info::getCreatedAt, targetTime, LocalDateTime.now());

    Page<Info> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
    Page<Info> pageResult = infoMapper.selectPage(page, lambdaQueryWrapper);
    List<Info> items = pageResult.getRecords();
    long total = pageResult.getTotal();
    PageResult<Info> infoPageResult = new PageResult<>(items, total, pageParams.getPageNo(),
        pageParams.getPageSize());
    return infoPageResult;
  }

  @Override
  public LocalDateTime getDuration(int timePeriod) {
    if (timePeriod == 1) {
      return LocalDateTime.now().minusDays(1);
    }
    if (timePeriod == 2) {
      return LocalDateTime.now().minusWeeks(1);
    }
    if (timePeriod == 3) {
      return LocalDateTime.now().minusWeeks(2);
    }
    if (timePeriod == 4) {
      return LocalDateTime.now().minusMonths(1);
    }
    if (timePeriod == 5) {
      return LocalDateTime.now().minusMonths(3);
    }
    if (timePeriod == 6) {
      return LocalDateTime.now().minusMonths(6);
    }
    if (timePeriod == 7) {
      return LocalDateTime.now().minusYears(1);
    }
    return LocalDateTime.of(LocalDateTime.now().getYear(), Month.JANUARY, 1, 0, 0, 0);
  }
}
