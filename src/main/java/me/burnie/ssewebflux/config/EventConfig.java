package me.burnie.ssewebflux.config;


import io.netty.util.concurrent.SingleThreadEventExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 이벤트 publisher configuration
 */
@Configuration
public class EventConfig{

    @Bean
    public Executor eventPublishExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // Configure pool size as needed
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("SampleEventExecutor-");
        executor.initialize();
        return executor;
    }



}
