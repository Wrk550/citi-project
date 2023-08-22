package edu.hnu.client.api.controller;

import edu.hnu.base.module.R;
import edu.hnu.client.service.InfoService;
import edu.hnu.client.service.OwnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

  @Autowired
  private OwnService ownService;

  @ApiOperation("添加客户")
  @ResponseBody
  @GetMapping("client/add")
  public R addClient(@RequestParam("clientName") String clientName,
      @RequestParam("phoneNumber") String phoneNumber) {
      return infoService.addClient(clientName, phoneNumber);
  }

  @ApiOperation("删除客户")
  @ResponseBody
  @GetMapping("client/delete")
  public R deleteClient(@RequestBody Integer[] ids) {
    for (Integer id : ids) {
      if (ownService.getById(id)) {
        return R.error("id为：" + id + "的客户仍有股票未出售，删除失败");
      }
    }
    infoService.removeByIds(Arrays.asList(ids));
    return R.ok("删除成功");
  }
}
