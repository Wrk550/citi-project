package edu.hnu.ucenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hnu.ucenter.model.po.XcRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itcast
 */
@Mapper
@Repository
public interface XcRoleMapper extends BaseMapper<XcRole> {

}
