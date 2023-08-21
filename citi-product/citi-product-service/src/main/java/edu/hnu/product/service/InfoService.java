package edu.hnu.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.base.module.R;
import edu.hnu.product.module.dto.QueryProductParamsDto;
import edu.hnu.product.module.po.Info;
import io.swagger.models.auth.In;
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
  public PageResult<Info> list(PageParams pageParams);
  public Info getByRIC(String ric);
  public Info getByTicker(String ticker);
  public R add(String name, String ticker, String description, String issueSector, BigDecimal price, String ric);

  /**
   * 股票分页查询
   * @param pageParams 分页查询参数
   * @param queryProductParamsDto 体检查询参数
   * @return 分页查询结果
   */
  public PageResult<Info> queryInfoList(PageParams pageParams, QueryProductParamsDto queryProductParamsDto);
}
