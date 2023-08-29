package edu.hnu.trade.conrtoller;

import edu.hnu.trade.service.LedgerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
}
