package edu.hnu.client.api.controller;

import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.base.module.R;
import edu.hnu.client.model.dto.QueryOwnParamsDto;
import edu.hnu.client.model.po.Own;
import edu.hnu.client.service.OwnService;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("own")
public class OwnController {

  @Autowired
  private OwnService ownService;

  @ApiOperation("获取客户持有对应股票数量")
  @ResponseBody
  @GetMapping("client/getSize")
  public Integer getSize(@RequestParam("clientId") Integer clientId,
      @RequestParam("productId") Integer productId) {
    return ownService.getByProductIdAndClientId(productId, clientId);
  }

  @ApiOperation("更新客户持有对应股票数量")
  @ResponseBody
  @GetMapping("client/updateSize")
  public void updateSize(@RequestParam("clientId") Integer clientId,
      @RequestParam("productId") Integer productId, @RequestParam("size") Integer size, @RequestParam("operator") Integer operator) {
    ownService.updateByProductIdAndClientId(productId, clientId, size, operator);
  }

  @ApiOperation("分页按条件查询客户持有股票")
  @ResponseBody
  @PostMapping("client/list")
  public R listOwn(PageParams pageParams,
      @RequestBody(required = false) QueryOwnParamsDto queryOwnParamsDto) {
    PageResult<Own> pageResult = ownService.queryInfoList(pageParams, queryOwnParamsDto);
    return R.ok("查询成功", pageResult);
  }

}
