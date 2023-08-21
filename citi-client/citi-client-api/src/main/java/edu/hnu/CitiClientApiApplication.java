package edu.hnu;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc
@SpringBootApplication
@MapperScan("edu.hnu.client.mapper")
public class CitiClientApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(CitiClientApiApplication.class, args);
  }

}
