package edu.hnu.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hnu.product.module.po.Stock;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wrk
 */

@Mapper
public interface StockMapper extends BaseMapper<Stock> {

}
