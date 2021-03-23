package org.ac.cst8277.ahmed.basit.config;

import feign.Request.Options;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultFeignConfig {
    private int connectionTimeout = 10000;
    private int readTimeout = 200000;

    public DefaultFeignConfig() {
    }

    @Bean
    public Options options() {
        return new Options(this.connectionTimeout, this.readTimeout);
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}
