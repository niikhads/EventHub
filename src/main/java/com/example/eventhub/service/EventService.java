package com.example.eventhub.service;

import com.example.eventhub.dto.request.EventRequest;
import com.example.eventhub.dto.response.EventResponse;
import com.example.eventhub.dto.response.BaseResponse;

import java.util.List;

public interface EventService {
    public BaseResponse<List<EventResponse>> getAllEvents() ;
    public BaseResponse<EventResponse> getEventById(Long id) ;
    public BaseResponse<EventResponse> createEvent(EventRequest eventRequest) ;
    public BaseResponse<EventResponse> updateEvent(Long id, EventRequest eventRequest) ;
    public BaseResponse<Void> deleteEvent(Long id) ;



    }
