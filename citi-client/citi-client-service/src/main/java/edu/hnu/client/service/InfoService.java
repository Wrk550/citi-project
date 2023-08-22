package edu.hnu.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hnu.base.module.R;
import edu.hnu.client.model.po.Info;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wrk
 * @since 2023-08-22
 */
public interface InfoService extends IService<Info> {

  /**
   * 添加客户
   * @param clientName 客户名称
   * @param phoneNumber 客户手机号码
   * @return
   */
  public R addClient(String clientName, String phoneNumber);

}
