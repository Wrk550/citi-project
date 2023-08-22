package edu.hnu.client.api.controller;

import edu.hnu.client.service.OwnService;
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
@RequestMapping("own")
public class OwnController {

    @Autowired
    private OwnService  ownService;
}
