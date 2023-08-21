package edu.hnu.trade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hnu.base.module.R;
import edu.hnu.trade.feignclient.ProductServiceClient;
import edu.hnu.trade.mapper.InfoMapper;
import edu.hnu.trade.model.po.Info;
import edu.hnu.trade.service.InfoService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
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

  @Override
  public R sell(String clientName, String ticker, String ric, Integer size, String currency,
      Integer htPt) {
    if (ric.equals("") && ticker.equals("")) {
      return R.error("请输入RIC或股票代码以确认将要进行操作的股票");
    }
    Info info = new Info();
    info.setClientName(clientName);
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
    return R.ok("卖出成功", info);
  }

  @Override
  public R buy(String clientName, String ticker, String ric, Integer size, String currency,
      Integer htPt) {
    if (ric.equals("") && ticker.equals("")) {
      return R.error("请输入RIC或股票代码以确认将要进行操作的股票");
    }
    Info info = new Info();
    info.setClientName(clientName);
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
    return R.ok("买入成功", info);
  }
}