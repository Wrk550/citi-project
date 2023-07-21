package edu.hnu.ucenter.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author itcast
 */
@Data
@TableName("xc_user_role")
public class XcUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private String roleId;

    private LocalDateTime createTime;

    private String creator;


}
