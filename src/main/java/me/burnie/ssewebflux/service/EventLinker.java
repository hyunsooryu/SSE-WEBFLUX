package me.burnie.ssewebflux.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.burnie.ssewebflux.model.SampleEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventLinker {
    private final ApplicationEventPublisher applicationEventPublisher;

    //여기서 이벤트를 퍼블리싱 해주는 식(Kafka, RabbitMq, Redis) 혹은 DB 등
   @Async
    public void publishEvent(SampleEvent sampleEvent){
        log.info(Thread.currentThread().getName() + "is now publising the event");
        applicationEventPublisher.publishEvent(sampleEvent);
    }

}
