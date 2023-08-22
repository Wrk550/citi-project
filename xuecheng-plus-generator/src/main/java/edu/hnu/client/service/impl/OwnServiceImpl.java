package edu.hnu.client.service.impl;

import edu.hnu.client.model.po.Own;
import edu.hnu.client.mapper.OwnMapper;
import edu.hnu.client.service.OwnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wrk
 */
@Slf4j
@Service
public class OwnServiceImpl extends ServiceImpl<OwnMapper, Own> implements OwnService {

}
