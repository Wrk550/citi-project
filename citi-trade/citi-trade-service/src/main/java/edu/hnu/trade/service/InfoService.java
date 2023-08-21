package edu.hnu.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.base.module.R;
import edu.hnu.product.module.dto.QueryProductParamsDto;
import edu.hnu.trade.model.dto.QueryTradeParamsDto;
import edu.hnu.trade.model.po.Info;
import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wrk
 * @since 2023-08-17
 */
public interface InfoService extends IService<Info> {

  public R sell(String clientName, String ticker, String ric, Integer size, String currency, Integer htPt);

  /**
   * 买入股票
   * @param clientName 客户名称
   * @param ticker 股票代码
   * @param ric RIC代码
   * @param size 买入数量
   * @param currency 货币种类
   * @param htPt 交易平台
   * @return
   */
  public R buy(String clientName, String ticker, String ric, Integer size, String currency, Integer htPt);

  /**
   * 分页查询交易记录
   * @param pageParams 分页查询参数
   * @param queryTradeParamsDto 条件查询参数
   * @return 分页查询结果
   */
  public PageResult<Info> queryInfoList(PageParams pageParams, QueryTradeParamsDto queryTradeParamsDto);
}
