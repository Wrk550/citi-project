package edu.hnu.trade.feignclient;

import edu.hnu.client.model.po.Info;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@FeignClient(value = "client-api")
public interface ClientServiceClient {

  @GetMapping("client/own/client/getSize")
  public Integer getSize(@RequestParam("clientId") Integer clientId,
      @RequestParam("productId") Integer productId);

  @GetMapping("client/own/client/updateSize")
  public void updateSize(@RequestParam("clientId") Integer clientId,
      @RequestParam("productId") Integer productId, @RequestParam("size") Integer size, @RequestParam("operator") Integer operator);

  @PostMapping("client/info/client/list")
  public List<Info> getById (@RequestParam("id") Integer id);
}
