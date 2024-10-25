package com.example.eventhub.service.impl;

import com.example.eventhub.dto.request.EventRequest;
import com.example.eventhub.dto.response.BaseResponse;
import com.example.eventhub.dto.response.EventResponse;
import com.example.eventhub.entity.Category;
import com.example.eventhub.entity.EventEntity;
import com.example.eventhub.enums.EventStatus;
import com.example.eventhub.exception.EventException;
import com.example.eventhub.mapper.EventMapper;
import com.example.eventhub.repository.CategoryRepository;
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

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final CategoryRepository categoryRepository;

    // Constructor Injection
    @Autowired
    public EventImplService(EventRepository eventRepository, EventMapper eventMapper, CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.categoryRepository = categoryRepository;
    }

    public BaseResponse<List<EventResponse>> getAllEvents() {
        try {
            logger.info("Fetching all events from database.");

            // Fetch all events from the repository
            List<EventEntity> eventEntities = eventRepository.findAll();

            if (eventEntities.isEmpty()) {
                throw new EventException("No events found in the system.");
            }

            // Convert EventEntity to EventResponse
            List<EventResponse> responses = eventEntities.stream()
                    .map(eventMapper::toResponse)

                    .collect(Collectors.toList());

            logger.info("Fetched {} events successfully.", responses.size());
            return new BaseResponse<>(200, "Events fetched successfully", new BaseResponse.ResponseData<>(responses));

        } catch (EventException e) {
            logger.error("Error occurred while fetching events: {}", e.getMessage());
            throw e;

      }
//        catch (Exception e) {
//            logger.error("Unexpected error occurred: {}", e.getMessage());
//            throw new EventException("Failed to fetch events due to unexpected error.");
//        }
    }

    public BaseResponse<EventResponse> createEvent(EventRequest eventRequest) {
        logger.info("Creating new event: {}", eventRequest);

        // categoryId ilə category obyektini bazadan tam olaraq tapırıq
        Category category = categoryRepository.findById(eventRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        // EventRequest-dən EventEntity-ə çevrilir
        EventEntity entity = eventMapper.toEntity(eventRequest);

        // category obyektini tam olaraq doldururuq
        entity.setCategory(category);

        // Entity-nin repository-də saxlanılması
        EventEntity savedEntity = eventRepository.save(entity);

        // Saxlanmış entity-ni EventResponse-a çevirmək
        EventResponse response = eventMapper.toResponse(savedEntity);

        logger.info("Event created successfully with ID: {}", savedEntity.getId());
        return new BaseResponse<>(200, "Event successfully created", new BaseResponse.ResponseData<>(response));
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
            return new BaseResponse<>(200, "Event found", new BaseResponse.ResponseData<>(response));
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
            return new BaseResponse<>(200, "Event successfully updated", new BaseResponse.ResponseData<>(response));
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

            if (!eventEntity.isPresent()) {
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
