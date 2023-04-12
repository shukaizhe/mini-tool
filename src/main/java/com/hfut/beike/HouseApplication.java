package com.hfut.beike;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Chenzh
 */

/**
 * 启用Hasor
 */
@EnableScheduling
@MapperScan("com.hfut.beike.dao")
@SpringBootApplication
@EnableEncryptableProperties
public class HouseApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseApplication.class, args);
    }
}
