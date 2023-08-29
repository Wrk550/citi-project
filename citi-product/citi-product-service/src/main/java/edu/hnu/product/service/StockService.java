package edu.hnu.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hnu.product.module.po.Stock;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wrk
 * @since 2023-08-22
 */
public interface StockService extends IService<Stock> {

  /**
   * 根据股票id获取库存
   *
   * @param productId 股票id
   * @return 股票库存
   */
  public Integer getInventory(Integer productId);

  /**
   * 售出股票，库存减少
   * @param productId 股票id
   * @param size 售出数量
   */
  public void decreaseInventory(Integer productId, Integer size);
}
