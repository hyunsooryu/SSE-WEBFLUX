package me.burnie.ssewebflux;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.burnie.ssewebflux.config.FluxConfig;
import me.burnie.ssewebflux.service.EventProvider;
import me.burnie.ssewebflux.model.SampleEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Slf4j
public class Scheduler {

    private final Sinks.Many<SampleEvent> eventSink;

    private final EventProvider eventProvider;
    private AtomicInteger count = new AtomicInteger(0);

    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedRate = 1)
    public void pushEvents(){
        log.info("now subscriber's count : " + FluxConfig.CLIENT_COUNT);
        var nowEvent = eventProvider.getNowEvent();
        eventSink.tryEmitNext(nowEvent);
    }

}
