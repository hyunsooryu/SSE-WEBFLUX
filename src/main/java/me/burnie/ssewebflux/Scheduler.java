package me.burnie.ssewebflux;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.burnie.ssewebflux.config.FluxConfig;
import me.burnie.ssewebflux.model.EventProvider;
import me.burnie.ssewebflux.model.SampleEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Slf4j
public class Scheduler {

    private final Sinks.Many<SampleEvent> eventSink;

    private final EventProvider eventProvider;
    private AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedRate = 1000)
    public void pushEvents(){
        log.info("now subscriber's count : " + FluxConfig.CLIENT_COUNT);
        var nowEvent = eventProvider.getNowEvent();
        eventSink.tryEmitNext(nowEvent);
    }

}
