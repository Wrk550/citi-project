package edu.hnu.trade.feignclient;


import edu.hnu.product.module.po.Info;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "product-api")
public interface ProductServiceClient {
  @GetMapping("/product/info/product/getByRIC")
  public Info getByRIC(@RequestParam("ric") String ric);

  @GetMapping("/product/info/product/getByTicker")
  public Info getByTicker(@RequestParam("ticker") String ticker);

  @GetMapping("/product/stock/product/getInventory")
  public Integer getInventory(@RequestParam("productId") Integer productId);

  @GetMapping("/product/stock/product/updateInventory")
  public void updateInventory(@RequestParam("productId") Integer productId, @RequestParam("size") Integer size);
}
