package edu.hnu.trade.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryTradeParamsDto {
  //客户名称
  private String clientName;
  //出售or买入
  private String clientSide;
  //股票代码
  private String ticker;
  //RIC代码
  private String ric;
  //货币种类
  private String currency;
  //部门
  private String issueSector;
  //交易平台
  private String htPt;
}
