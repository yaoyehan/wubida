package com.yyh.wubida;

import com.itheima.pinda.common.converter.EnumDeserializer;
import com.itheima.pinda.utils.DateUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableFeignClients
public class WebCourierApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebCourierApplication.class, args);
    }
}
