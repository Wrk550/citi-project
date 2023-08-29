package edu.hnu.trade.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.hnu.base.module.R;
import edu.hnu.trade.model.po.Ledger;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wrk
 * @since 2023-08-29
 */
public interface LedgerService extends IService<Ledger> {

  /**
   * 更新统计图数据
   * @param localDateTime 当前时间
   */
  public R updateData(LocalDateTime localDateTime);

}
