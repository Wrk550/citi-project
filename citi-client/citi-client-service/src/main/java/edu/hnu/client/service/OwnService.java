package edu.hnu.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hnu.base.module.R;
import edu.hnu.client.model.po.Own;

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
}
