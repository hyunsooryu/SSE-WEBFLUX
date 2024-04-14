package me.burnie.ssewebflux.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.burnie.ssewebflux.model.SampleEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class EventProvider {
    ConcurrentHashMap<String, SampleEvent> cache = new ConcurrentHashMap<>();

    public EventProvider(){
        var sampleEvent =  new SampleEvent();
        sampleEvent.setMessage(LocalDateTime.now().toString());
        sampleEvent.setQuestion("sampleQuestion");
        sampleEvent.setName("sampleName");
        sampleEvent.setAnswers(List.of("A","B","C","D","E"));
        cache.put("now", sampleEvent);
    }

    @EventListener(SampleEvent.class)
    void listen(SampleEvent sampleEvent){
        cache.put("now", sampleEvent);
    }

    public SampleEvent getNowEvent(){
        return cache.get("now");
    }

}
