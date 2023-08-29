package edu.hnu.trade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hnu.trade.mapper.LedgerMapper;
import edu.hnu.trade.model.po.Ledger;
import edu.hnu.trade.service.LedgerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wrk
 */
@Slf4j
@Service
public class LedgerServiceImpl extends ServiceImpl<LedgerMapper, Ledger> implements LedgerService {

}
