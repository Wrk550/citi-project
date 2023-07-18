package edu.hnu.base.module;

import lombok.Data;
import lombok.ToString;

/**
 * @author wrk
 * @description 分页查询参数
 */
@Data
@ToString
public class PageParams {
  private Long pageNo = 1L;
  private Long pageSize = 30L;

  public PageParams() {
  }

  public PageParams(Long pageNo, Long pageSize) {
    this.pageNo = pageNo;
    this.pageSize = pageSize;
  }
}
