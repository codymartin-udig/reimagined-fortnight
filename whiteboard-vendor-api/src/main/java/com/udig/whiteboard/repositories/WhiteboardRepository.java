package com.udig.whiteboard.repositories;

import com.udig.whiteboard.models.Whiteboard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface WhiteboardRepository extends MongoRepository<Whiteboard, String> {
    public List<Whiteboard> findByManufacturer(final String manufacturer);
    public void deleteById(final String id);
}
