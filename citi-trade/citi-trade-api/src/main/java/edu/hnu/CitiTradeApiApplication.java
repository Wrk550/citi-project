package edu.hnu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableSwagger2Doc
@SpringBootApplication
@EnableFeignClients(basePackages={"edu.hnu.trade.feignclient"})
@MapperScan("edu.hnu.trade.mapper")
public class CitiTradeApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(CitiTradeApiApplication.class, args);
  }

}
