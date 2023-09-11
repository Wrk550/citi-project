package edu.hnu.trade.conrtoller;

import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.base.module.R;
import edu.hnu.trade.model.dto.QueryTradeParamsDto;
import edu.hnu.trade.model.po.Info;
import edu.hnu.trade.service.InfoService;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wrk
 */
@Slf4j
@RestController
@RequestMapping("info")
public class InfoController {

  @Autowired
  private InfoService infoService;

  @ApiOperation("出售股票")
  @PostMapping("/trade/sell")
  public R sell(@RequestParam("clientId") Integer clientId,
      @RequestParam("ticker") String ticker, @RequestParam("ric") String ric,
      @RequestParam("size") Integer size, @RequestParam("currency") String currency,
      @RequestParam("htPt") Integer htPt) {
    return infoService.sell(clientId, ticker, ric, size, currency, htPt);
  }

  @ApiOperation("买入股票")
  @PostMapping("/trade/buy")
  public R buy(@RequestParam("clientId") Integer clientId,
      @RequestParam("ticker") String ticker, @RequestParam("ric") String ric,
      @RequestParam("size") Integer size, @RequestParam("currency") String currency,
      @RequestParam("htPt") Integer htPt) {
    return infoService.buy(clientId, ticker, ric, size, currency, htPt);
  }

  @ApiOperation("分页查询交易记录")
  @PostMapping("/trade/list")
  public R list(PageParams pageParams, @RequestBody(required=false) QueryTradeParamsDto queryTradeParamsDto) {
    PageResult<Info> infoPageResult = infoService.queryInfoList(pageParams, queryTradeParamsDto);
    BigDecimal sellSum = new BigDecimal("0");
    BigDecimal buySum = new BigDecimal("0");
    int sellSize = 0;
    int buySize = 0;
    for (Info item : infoPageResult.getItems()) {
      if (item.getClientSide().equals("sell")) {
        sellSum = sellSum.add(item.getNotionalUsd());
        sellSize += item.getSize();
      }
      if (item.getClientSide().equals("buy")) {
        buySum = buySum.add(item.getNotionalUsd());
        buySize += item.getSize();
      }
    }
    HashMap<String, Object> res = new HashMap<>();
    res.put("pageResult", infoPageResult);
    res.put("TotalBuy", buySize);
    res.put("TotalSell", sellSize);
    res.put("NetQuantity", buySize - sellSize);
    res.put("TotalSellNotional", sellSum);
    res.put("TotalBuyNotional", buySum);
    res.put("NetNotional", buySum.subtract(sellSum));
    return R.ok("查询成功", res);
  }
}
