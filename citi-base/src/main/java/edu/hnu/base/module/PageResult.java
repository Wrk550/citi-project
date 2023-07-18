package edu.hnu.base.module;

import java.io.Serializable;
import java.util.List;

/**
 * @author wrk
 * @param <T>
 * @description 分页查询结果
 */

public class PageResult<T> implements Serializable{
  //数据列表
  private List<T> items;
  //总记录数
  private long counts;
  //当前页码
  private long page;
  //每页记录数
  private long pageSize;

  public PageResult(List<T> items, long counts, long page, long pageSize) {
    this.counts = counts;
    this.items = items;
    this.page = page;
    this.pageSize = pageSize;
  }
}
