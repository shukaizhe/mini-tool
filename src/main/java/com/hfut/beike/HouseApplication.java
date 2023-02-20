package com.hfut.beike;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import net.hasor.spring.boot.EnableHasor;
import net.hasor.spring.boot.EnableHasorWeb;
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
@EnableHasor    // 在Spring 中启用 Hasor
@EnableHasorWeb // 将 hasor-web 配置到 Spring 环境中，Dataway 的 UI 是通过 hasor-web 提供服务。
@EnableScheduling
@MapperScan("com.hfut.beike.dao")
@SpringBootApplication
@EnableEncryptableProperties
public class HouseApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseApplication.class, args);
    }
}
