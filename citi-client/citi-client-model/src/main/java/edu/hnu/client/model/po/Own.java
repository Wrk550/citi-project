package edu.hnu.client.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author wrk
 */
@Data
@TableName("client_own")
public class Own implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户id
     */
    private Integer clientId;

    /**
     * 持有的股票id
     */
    private Integer productId;

    /**
     * 持有数量
     */
    private Integer size;


}
