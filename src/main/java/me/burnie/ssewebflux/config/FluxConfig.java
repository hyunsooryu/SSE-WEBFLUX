package me.burnie.ssewebflux.config;

import lombok.extern.slf4j.Slf4j;
import me.burnie.ssewebflux.model.SampleEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@Slf4j
public class FluxConfig {
    public static AtomicInteger CLIENT_COUNT = new AtomicInteger(0);

    @Bean
    public Sinks.Many<SampleEvent> sink(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Flux<SampleEvent> eventBroadCast(Sinks.Many<SampleEvent> sink){
        return sink.asFlux()
                .doOnSubscribe(subscription -> {
                   CLIENT_COUNT.incrementAndGet();
                  // log.info("subscriber is now on => " + subscription.toString());
                }).doOnCancel(()->{
                   CLIENT_COUNT.decrementAndGet();
                    log.info("subscriber is now off");
                }).doOnComplete(()->{
                    log.info("subscribing is finished");
                });
    }
}
