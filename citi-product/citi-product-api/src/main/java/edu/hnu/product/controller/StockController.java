package edu.hnu.product.controller;

import edu.hnu.product.service.StockService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wrk
 */
@Slf4j
@RestController
@RequestMapping("stock")
public class StockController {

  @Autowired
  private StockService stockService;

  @ApiOperation("根据产品id获取库存")
  @ResponseBody
  @GetMapping("/product/getInventory")
  public Integer getInventory(@RequestParam("productId") Integer productId) {
    return stockService.getInventory(productId);
  }

  @ApiOperation("根据产品id更新库存")
  @ResponseBody
  @GetMapping("/product/updateInventory")
  public void updateInventory(@RequestParam("productId") Integer productId, @RequestParam("size") Integer size) {
    stockService.decreaseInventory(productId, size);
  }
}
