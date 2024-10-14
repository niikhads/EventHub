package com.example.eventhub.controller;

import com.example.eventhub.dto.request.EventRequest;
import com.example.eventhub.dto.response.BaseResponse;
import com.example.eventhub.dto.response.EventResponse;
import com.example.eventhub.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    @Autowired
    private EventService eventService;


    @GetMapping
    public ResponseEntity<BaseResponse<List<EventResponse>>> getAllEvents() {
        BaseResponse<List<EventResponse>> eventResponse = eventService.getAllEvents();
        return ResponseEntity.status(eventResponse.getCode()).body(eventResponse);
    }


    @PostMapping
    public ResponseEntity<BaseResponse<EventResponse>> createEvent(@RequestBody EventRequest eventRequest) {
        BaseResponse<EventResponse> eventResponse = eventService.createEvent(eventRequest);
        return ResponseEntity.status(eventResponse.getCode()).body(eventResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<EventResponse>> getEventById(@PathVariable Long id) {
        BaseResponse<EventResponse> eventResponse = eventService.getEventById(id);
        return ResponseEntity.status(eventResponse.getCode()).body(eventResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<EventResponse>> updateEvent(@PathVariable Long id, @RequestBody EventRequest eventRequest) {
        BaseResponse<EventResponse> eventResponse = eventService.updateEvent(id, eventRequest);
        return ResponseEntity.status(eventResponse.getCode()).body(eventResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteEvent(@PathVariable Long id) {
        BaseResponse<Void> eventResponse = eventService.deleteEvent(id);
        return ResponseEntity.status(eventResponse.getCode()).body(eventResponse);
    }
}

