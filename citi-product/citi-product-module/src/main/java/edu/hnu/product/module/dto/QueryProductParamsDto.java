package edu.hnu.product.module.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryProductParamsDto {
  //股票名称
  private String name;
  //股票代码
  private String ticker;
  //所属部门
  private String issueSector;
  //RIC代码
  private String ric;
}
