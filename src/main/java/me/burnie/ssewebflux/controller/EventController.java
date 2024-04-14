package me.burnie.ssewebflux.controller;


import lombok.RequiredArgsConstructor;
import me.burnie.ssewebflux.model.SampleEvent;
import me.burnie.ssewebflux.service.EventLinker;
import me.burnie.ssewebflux.service.EventProvider;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final Sinks.Many<SampleEvent> eventSink;
    private final Flux<SampleEvent> eventFlux;
    private final EventLinker eventLinker;

    /**
     * For publishing Event
     * @param sampleEvent
     * @return
     */

    @PostMapping("/update")
    public Mono<Void> updateEvent(@RequestBody SampleEvent sampleEvent){
        eventLinker.publishEvent(sampleEvent);
        return Mono.empty();
    }

    @GetMapping(value = "/sub", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<SampleEvent> getGameUpdates() {
        return this.eventFlux;
    }

}
