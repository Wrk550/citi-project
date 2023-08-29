package edu.hnu.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hnu.base.module.R;
import edu.hnu.trade.mapper.InfoMapper;
import edu.hnu.trade.mapper.LedgerMapper;
import edu.hnu.trade.model.po.Info;
import edu.hnu.trade.model.po.Ledger;
import edu.hnu.trade.service.LedgerService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class LedgerServiceImpl extends ServiceImpl<LedgerMapper, Ledger> implements LedgerService {

  @Autowired
  InfoMapper infoMapper;

  @Autowired
  LedgerMapper ledgerMapper;

  @Override
  public R updateData(LocalDateTime localDateTime) {
    LocalDateTime before = localDateTime.minusDays(1);
    LambdaQueryWrapper<Info> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.between(Info::getCreatedAt, before, localDateTime);
    List<Info> list = infoMapper.selectList(lambdaQueryWrapper);
    BigDecimal sellSum = new BigDecimal("0");
    BigDecimal buySum = new BigDecimal("0");
    for (Info item : list) {
      if (item.getClientSide().equals("sell")) {
        sellSum = sellSum.add(item.getNotionalUsd());
      }
      if (item.getClientSide().equals("buy")) {
        buySum = buySum.add(item.getNotionalUsd());
      }
    }
    Ledger ledger = new Ledger();
    ledger.setTotalBuyNotional(buySum);
    ledger.setTotalSellNotional(sellSum);
    ledger.setDate(before.toLocalDate());
    ledger.setNetNotional(buySum.subtract(sellSum));
    ledgerMapper.insert(ledger);
    Map<String, Object> res = new HashMap<>();
    res.put("list", list);
    res.put("sellSum", sellSum);
    res.put("buySum", buySum);
    res.put("ledger", ledger);
    return R.ok("定时任务已执行，插入成功", res);
  }
}
