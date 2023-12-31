package edu.hnu;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc
@MapperScan("edu.hnu.product.mapper")
@SpringBootApplication
public class CitiProductApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(CitiProductApiApplication.class, args);
  }

}
