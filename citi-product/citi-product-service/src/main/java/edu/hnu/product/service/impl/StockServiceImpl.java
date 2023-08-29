package edu.hnu.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hnu.product.mapper.StockMapper;
import edu.hnu.product.module.po.Stock;
import edu.hnu.product.service.StockService;
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
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

  @Autowired
  StockMapper stockMapper;

  @Override
  public Integer getInventory(Integer productId) {
    LambdaQueryWrapper<Stock> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(Stock::getProductId, productId);
    Stock stock = stockMapper.selectOne(lambdaQueryWrapper);
    return stock.getProductInventory();
  }

  @Override
  public void decreaseInventory(Integer productId, Integer size) {
    LambdaQueryWrapper<Stock> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(Stock::getProductId, productId);
    Stock stock = stockMapper.selectOne(lambdaQueryWrapper);
    stock.setProductInventory(stock.getProductInventory() - size);
    stockMapper.updateById(stock);
  }


}
