package edu.hnu.product;

import edu.hnu.base.module.PageParams;
import edu.hnu.base.module.PageResult;
import edu.hnu.product.mapper.InfoMapper;
import edu.hnu.product.module.dto.QueryProductParamsDto;
import edu.hnu.product.module.po.Info;
import edu.hnu.product.service.InfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductTest {
  @Autowired
  InfoMapper infoMapper;

  @Autowired
  InfoService infoService;

  @Test
  public void testMapper() {
    Info info = infoMapper.selectById(1);
//    Assertions.assertNotNull(info);
    QueryProductParamsDto queryProductParamsDto = new QueryProductParamsDto();
    queryProductParamsDto.setName("测试");
    PageParams pageParams = new PageParams();
    pageParams.setPageNo(2L);
    pageParams.setPageSize(1L);
    PageResult<Info> infoPageResult = infoService.queryInfoList(pageParams, queryProductParamsDto);
    System.out.println(infoPageResult.getItems());
  }

}
