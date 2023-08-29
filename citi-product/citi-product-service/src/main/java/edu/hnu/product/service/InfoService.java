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

  /**
   * 根据RIC代码获取对应的股票产品
   * @param ric RIC代码
   * @return 股票对象
   */
  public Info getByRIC(String ric);

  /**
   * 通过缓存获取
   * @param ric
   * @return
   */
  public Info getByRICCache(String ric);

  /**
   * 根据ticker获取对应的股票产品
   * @param ticker ticker代码
   * @return 股票对象
   */
  public Info getByTicker(String ticker);

  /**
   * 通过缓存获取
   * @param id
   * @return
   */
  public Info getByIdCache(Integer id);

  /**
   * 根据ticker获取对应的股票产品
   * @param id 股票id
   * @return 股票对象
   */
  public Info getById(Integer id);

  /**
   * 通过缓存获取
   * @param ticker
   * @return
   */
  public Info getByTickerCache(String ticker);

  /**
   * 添加股票
   * @param name 股票名称
   * @param ticker 股票代码
   * @param description 股票介绍
   * @param issueSector 所属部门
   * @param price 股票单价
   * @param ric RIC代码
   * @return Response对象
   */
  public R add(String name, String ticker, String description, String issueSector, BigDecimal price, String ric);

  /**
   * 股票分页查询
   * @param pageParams 分页查询参数
   * @param queryProductParamsDto 体检查询参数
   * @return 分页查询结果
   */
  public PageResult<Info> queryInfoList(PageParams pageParams, QueryProductParamsDto queryProductParamsDto);

}
