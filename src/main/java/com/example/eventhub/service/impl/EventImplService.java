package com.example.eventhub.service.impl;

import com.example.eventhub.dto.request.EventRequest;
import com.example.eventhub.dto.response.BaseResponse;
import com.example.eventhub.dto.response.EventResponse;
import com.example.eventhub.entity.EventEntity;
import com.example.eventhub.exception.EventException;
import com.example.eventhub.mapper.EventMapper;
import com.example.eventhub.repository.EventRepository;
import com.example.eventhub.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventImplService implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventImplService.class);

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper eventMapper;

    public BaseResponse<List<EventResponse>> getAllEvents() {
        try {
            logger.info("Fetching all events from database.");
            List<EventEntity> eventEntities = eventRepository.findAll();
            if (eventEntities.isEmpty()) {
                throw new EventException("No events found in the system.");
            }
            List<EventResponse> responses = eventEntities.stream()
                    .map(eventMapper::toResponse)
                    .collect(Collectors.toList());

            logger.info("Fetched {} events successfully.", responses.size());
            return new BaseResponse<>(200, "Events fetched successfully", responses);
        } catch (EventException e) {
            logger.error("Error occurred while fetching events: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            throw new EventException("Failed to fetch events due to unexpected error.");
        }
    }

    public BaseResponse<EventResponse> createEvent(EventRequest eventRequest) {
        try {
            logger.info("Creating new event: {}", eventRequest);
            EventEntity entity = eventMapper.toEntity(eventRequest);
            EventEntity savedEntity = eventRepository.save(entity);
            EventResponse response = eventMapper.toResponse(savedEntity);

            logger.info("Event created successfully with ID: {}", savedEntity.getId());
            return new BaseResponse<>(200, "Event successfully created", response);
        } catch (Exception e) {
            logger.error("Error occurred while creating event: {}", e.getMessage());
            throw new EventException("Failed to create event due to unexpected error.");
        }
    }

    public BaseResponse<EventResponse> getEventById(Long id) {
        try {
            logger.info("Fetching event by ID: {}", id);
            Optional<EventEntity> eventEntity = eventRepository.findById(id);

            if (!eventEntity.isPresent()) {
                throw new EventException("Event with ID " + id + " not found.");
            }

            EventResponse response = eventMapper.toResponse(eventEntity.get());
            logger.info("Event found with ID: {}", id);
            return new BaseResponse<>(200, "Event found", response);
        } catch (EventException e) {
            logger.error("Error occurred while fetching event: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            throw new EventException("Failed to fetch event due to unexpected error.");
        }
    }

    public BaseResponse<EventResponse> updateEvent(Long id, EventRequest eventRequest) {
        try {
            logger.info("Updating event with ID: {}", id);
            Optional<EventEntity> existingEvent = eventRepository.findById(id);

            if (!existingEvent.isPresent()) {
                throw new EventException("Event with ID " + id + " not found.");
            }

            EventEntity entityToUpdate = eventMapper.toEntity(eventRequest);
            entityToUpdate.setId(id);
            EventEntity updatedEntity = eventRepository.save(entityToUpdate);
            EventResponse response = eventMapper.toResponse(updatedEntity);

            logger.info("Event updated successfully with ID: {}", id);
            return new BaseResponse<>(200, "Event successfully updated", response);
        } catch (EventException e) {
            logger.error("Error occurred while updating event: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            throw new EventException("Failed to update event due to unexpected error.");
        }
    }

    public BaseResponse<Void> deleteEvent(Long id) {
        try {
            logger.info("Deleting event with ID: {}", id);
            Optional<EventEntity> eventEntity = eventRepository.findById(id);

            if (!eventEntity.isPresent()) { //isEmpty?
                throw new EventException("Event with ID " + id + " not found.");
            }

            eventRepository.deleteById(id);
            logger.info("Event with ID: {} successfully deleted", id);
            return new BaseResponse<>(200, "Event successfully deleted", null);
        } catch (EventException e) {
            logger.error("Error occurred while deleting event: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            throw new EventException("Failed to delete event due to unexpected error.");
        }
    }
}
