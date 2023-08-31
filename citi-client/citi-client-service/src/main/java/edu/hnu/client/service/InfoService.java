package edu.hnu.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
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

  /**
   * 客户信息分页查询
   * @param pageParams 分页查询参数
   * @return 分页查询结果
   */
  public PageResult<Info> queryInfoList(PageParams pageParams);

}
