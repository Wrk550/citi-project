package edu.hnu.trade.conrtoller;

import edu.hnu.base.module.R;
import edu.hnu.trade.model.po.Info;
import edu.hnu.trade.model.po.Ledger;
import edu.hnu.trade.service.LedgerService;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wrk
 */
@Slf4j
@RestController
@RequestMapping("ledger")
public class LedgerController {

    @Autowired
    private LedgerService  ledgerService;

    @ApiOperation("获取柱状图所需数据")
    @GetMapping("/trade/list")
    public R list() {
        List<Ledger> list = ledgerService.list();
        if (list.size() == 0) {
            return R.error("不存在数据，获取失败");
        }
        return R.ok("获取成功", list);
    }
}
