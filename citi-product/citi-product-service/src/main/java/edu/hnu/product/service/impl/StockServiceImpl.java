package edu.hnu.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hnu.product.mapper.StockMapper;
import edu.hnu.product.module.po.Stock;
import edu.hnu.product.service.StockService;
import lombok.extern.slf4j.Slf4j;
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
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

}
