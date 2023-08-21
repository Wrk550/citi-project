package edu.hnu.trade.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hnu.base.module.R;
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
  public R buy(String clientName, String ticker, String ric, Integer size, String currency, Integer htPt);
}
