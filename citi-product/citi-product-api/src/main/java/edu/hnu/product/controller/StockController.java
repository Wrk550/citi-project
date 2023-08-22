package edu.hnu.product.controller;

import edu.hnu.product.service.StockService;
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
@RequestMapping("stock")
public class StockController {

    @Autowired
    private StockService  stockService;
}
