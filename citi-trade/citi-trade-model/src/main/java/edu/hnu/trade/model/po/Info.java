package edu.hnu.trade.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author wrk
 */
@Data
@TableName("trade_info")
public class Info implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 交易ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 日期
     */
    private LocalDate date;

    /**
     * 客户ID
     */
    private String clientId;

    /**
     * 客户操作
     */
    private String clientSide;

    /**
     * 股票代码
     */
    private String ticker;

    /**
     * RIC代码
     */
    private String ric;

    /**
     * 交易数量
     */
    private Integer size;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 名义美元=规模*价格(货币/美元汇率)
     */
    private BigDecimal notionalUsd;

    /**
     * 货币
     */
    private String currency;

    /**
     * 发行部门
     */
    private String issueSector;

    /**
     * 销售员
     */
    private String salesperson;

    /**
     * HT/PT
     */
    private String htPt;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;


}
