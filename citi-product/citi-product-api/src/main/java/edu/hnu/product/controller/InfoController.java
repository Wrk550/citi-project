package edu.hnu.product.controller;

import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.base.module.R;
import edu.hnu.product.module.dto.QueryProductParamsDto;
import edu.hnu.product.module.po.Info;
import edu.hnu.product.service.InfoService;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("info")
public class InfoController {

  @Autowired
  private InfoService infoService;

  @ApiOperation("根据RIC查找对应股票")
  @ResponseBody
  @GetMapping("/product/getByRIC")
  public Info getByRIC(@RequestParam("ric") String ric) {
    return infoService.getByRICCache(ric);
  }

  @ApiOperation("根据Ticker查找对应股票")
  @ResponseBody
  @GetMapping("/product/getByTicker")
  public Info getByTicker(@RequestParam("ticker") String ticker) {
    return infoService.getByTickerCache(ticker);
  }

  @ApiOperation("根据id查找对应股票")
  @ResponseBody
  @GetMapping("/product/getById")
  public Info getById(@RequestParam("id") Integer id) {
    return infoService.getByIdCache(id);
  }

  @ApiOperation("添加股票")
  @ResponseBody
  @PostMapping("/product/add")
  public R addProduct(@RequestParam("name") String name, @RequestParam("ticker") String ticker,
      @RequestParam("description") String description,
      @RequestParam("issueSector") String issueSector, @RequestParam("price") BigDecimal price,
      @RequestParam("ric") String ric) {
    return infoService.add(name, ticker, description, issueSector, price, ric);
  }

  @ApiOperation("删除股票")
  @ResponseBody
  @PostMapping("/product/delete")
  public R deleteProduct(@RequestBody Integer[] ids) {
    infoService.removeByIds(Arrays.asList(ids));
    return R.ok("删除成功");
  }

  @ApiOperation("修改股票")
  @ResponseBody
  @PostMapping("/product/update")
  public R updateProduct(@RequestBody Info info) {
    infoService.updateById(info);
    return R.ok("修改成功");
  }

  @ApiOperation("分页查询股票信息")
  @ResponseBody
  @PostMapping("/product/list")
  public R listProduct(PageParams pageParams, @RequestBody(required=false) QueryProductParamsDto queryProductParamsDto) {
    PageResult<Info> infoPageResult = infoService.queryInfoList(pageParams, queryProductParamsDto);
    return R.ok("查询成功", infoPageResult);
  }

}
