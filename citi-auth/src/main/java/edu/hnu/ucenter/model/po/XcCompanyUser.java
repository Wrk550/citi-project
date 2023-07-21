package edu.hnu.ucenter.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author itcast
 */
@Data
@TableName("xc_company_user")
public class XcCompanyUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String companyId;

    private String userId;


}
