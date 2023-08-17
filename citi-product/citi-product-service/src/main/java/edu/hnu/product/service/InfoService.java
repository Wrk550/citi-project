package edu.hnu.product.service;

import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.product.module.po.Info;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wrk
 * @since 2023-08-17
 */
public interface InfoService {
  public PageResult<Info> list(PageParams pageParams);
}
