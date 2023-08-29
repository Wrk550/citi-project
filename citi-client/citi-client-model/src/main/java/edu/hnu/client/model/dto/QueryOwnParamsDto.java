package edu.hnu.client.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryOwnParamsDto {
  //ticker代码
  private String ticker;
  //ric代码
  private String ric;
  //产品名称
  private String productName;
  //客户姓名
  private String clientName;
}
