package edu.hnu.trade.model.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

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
    private Integer id;

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
