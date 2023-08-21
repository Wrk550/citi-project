package edu.hnu.trade.conrtoller;

import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.base.module.R;
import edu.hnu.trade.model.dto.QueryTradeParamsDto;
import edu.hnu.trade.model.po.Info;
import edu.hnu.trade.service.InfoService;
import io.swagger.annotations.ApiOperation;
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
  public R sell(@RequestParam("clientName") String clientName,
      @RequestParam("ticker") String ticker, @RequestParam("ric") String ric,
      @RequestParam("size") Integer size, @RequestParam("currency") String currency,
      @RequestParam("htPt") Integer htPt) {
    return infoService.sell(clientName, ticker, ric, size, currency, htPt);
  }

  @ApiOperation("买入股票")
  @PostMapping("/trade/buy")
  public R buy(@RequestParam("clientName") String clientName,
      @RequestParam("ticker") String ticker, @RequestParam("ric") String ric,
      @RequestParam("size") Integer size, @RequestParam("currency") String currency,
      @RequestParam("htPt") Integer htPt) {
    return infoService.buy(clientName, ticker, ric, size, currency, htPt);
  }

  @ApiOperation("分页查询交易记录")
  @PostMapping("/trade/list")
  public R list(PageParams pageParams, @RequestBody(required=false) QueryTradeParamsDto queryTradeParamsDto) {
    PageResult<Info> infoPageResult = infoService.queryInfoList(pageParams, queryTradeParamsDto);
    return R.ok("查询成功", infoPageResult);
  }
}
