package edu.hnu.client.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("client_info")
public class Info implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户名
     */
    private String clientName;

    /**
     * 负责该客户的用户id
     */
    private String userId;

    /**
     * 客户的手机号码
     */
    private String phoneNumber;


}
