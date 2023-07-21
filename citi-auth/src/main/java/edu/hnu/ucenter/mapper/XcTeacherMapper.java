package edu.hnu.ucenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hnu.ucenter.model.po.XcTeacher;
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
public interface XcTeacherMapper extends BaseMapper<XcTeacher> {

}
