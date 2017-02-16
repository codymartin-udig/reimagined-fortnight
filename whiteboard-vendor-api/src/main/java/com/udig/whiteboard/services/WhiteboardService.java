package com.udig.whiteboard.services;

import com.udig.whiteboard.models.Whiteboard;
import com.udig.whiteboard.models.WhiteboardList;
import org.springframework.stereotype.Service;

@Service
public interface WhiteboardService {
    WhiteboardList getWhiteboards();
    WhiteboardList getWhiteboards(final int size, final int page);
    WhiteboardList getWhiteboardsByManufacturer(final String manufacturer);
    Whiteboard getWhiteboardById(final String id);
    Whiteboard updateWhiteboard(final Whiteboard whiteboard);

    Whiteboard createWhiteboard (Whiteboard whiteboard);

    boolean deleteById(final String id);
}
