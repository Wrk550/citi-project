package edu.hnu.client.feignclient;


import edu.hnu.product.module.po.Info;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "product-api")
public interface ProductServiceClient {
  @GetMapping("product/info/product/getById")
  public Info getById(@RequestParam("id") Integer id);
}
