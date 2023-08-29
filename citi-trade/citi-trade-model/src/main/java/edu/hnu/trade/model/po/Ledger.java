package edu.hnu.trade.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author wrk
 */
@Data
@TableName("trade_ledger")
public class Ledger implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 统计的日期
     */
    private LocalDate date;

    /**
     * 总共出售金额
     */
    private BigDecimal totalSellNotional;

    /**
     * 总共买入金额
     */
    private BigDecimal totalBuyNotional;

    /**
     * 差价
     */
    private BigDecimal netNotional;


}
