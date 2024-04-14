package me.burnie.ssewebflux.model;


import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EventProvider {
    ConcurrentHashMap<String,SampleEvent> cache = new ConcurrentHashMap<>();

    public EventProvider(){
        //cache init
    }

    public SampleEvent getNowEvent(){
        var sampleEvent =  new SampleEvent();
        sampleEvent.setMessage(LocalDateTime.now().toString());
        sampleEvent.setQuestion("sampleQuestion");
        sampleEvent.setName("sampleName");
        sampleEvent.setAnswers(List.of("A","B","C","D","E"));
        return sampleEvent;
    }






}
