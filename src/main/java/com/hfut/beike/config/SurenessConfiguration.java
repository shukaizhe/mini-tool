package com.hfut.beike.config;

import com.usthe.sureness.DefaultSurenessConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SurenessConfiguration {
    /**
     * new sureness default config bean
     * @return default config bean
     */
    @Bean
    public DefaultSurenessConfig surenessConfig() {
        return new DefaultSurenessConfig();
    }
}
