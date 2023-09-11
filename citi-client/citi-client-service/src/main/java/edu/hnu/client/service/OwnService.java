package edu.hnu.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.base.module.R;
import edu.hnu.client.model.dto.QueryOwnParamsDto;
import edu.hnu.client.model.po.Own;
import edu.hnu.product.module.dto.QueryProductParamsDto;
import edu.hnu.product.module.po.Info;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wrk
 * @since 2023-08-22
 */
public interface OwnService extends IService<Own> {

  /**
   * 根据客户Id查找客户
   * @param clientId 客户Id
   * @return 是否存在
   */
  public boolean getById(Integer clientId);

  /**
   * 根据产品id和客户id查找客户持有数量
   * @param clientId 客户Id
   * @return 客户持有对应股票数量
   */
  public Integer getByProductIdAndClientId(Integer productId, Integer clientId);

  /**
   * 根据产品id和客户id更新客户的持有数量
   * @param productId 产品id
   * @param clientId 客户id
   * @param size 更新后的持有数量
   */
  public void updateByProductIdAndClientId(Integer productId, Integer clientId, Integer size, Integer operator);

  /**
   * 客户持有股票分页查询
   * @param pageParams 分页查询参数
   * @param queryOwnParamsDto 条件查询参数
   * @return 分页条件查询结果
   */
  public PageResult<Own> queryInfoList(PageParams pageParams, QueryOwnParamsDto queryOwnParamsDto);
}
