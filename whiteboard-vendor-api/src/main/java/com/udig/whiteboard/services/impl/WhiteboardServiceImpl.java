package com.udig.whiteboard.services.impl;

import com.udig.whiteboard.models.Whiteboard;
import com.udig.whiteboard.models.WhiteboardList;
import com.udig.whiteboard.repositories.WhiteboardRepository;
import com.udig.whiteboard.services.WhiteboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WhiteboardServiceImpl implements WhiteboardService {
    @Autowired
    private WhiteboardRepository repository;

    @Override
    public WhiteboardList getWhiteboards () {
        final WhiteboardList result = new WhiteboardList();
        result.addAll(repository.findAll());
        return result;
    }

    @Override
    public WhiteboardList getWhiteboards (final int size, final int page) {
        final WhiteboardList result = new WhiteboardList();
        result.addAll(repository.findAll(new PageRequest(page-1, size)).getContent());
        return result;
    }

    @Override
    public WhiteboardList getWhiteboardsByManufacturer (final String manufacturer) {
        final WhiteboardList result = new WhiteboardList();
        result.addAll(repository.findByManufacturer(manufacturer));
        return result;
    }

    @Override
    public Whiteboard getWhiteboardById (final String id) {
        return repository.findOne(id);
    }


    @Override
    public Whiteboard updateWhiteboard (final Whiteboard whiteboard) {
        if(whiteboard.getId() == null || whiteboard.getId().equals(""))
            return createWhiteboard(whiteboard);

        final Whiteboard existing = getWhiteboardById(whiteboard.getId());

        if(null == whiteboard.getManufacturer()
                || "".equals(whiteboard.getManufacturer()))
            whiteboard.setManufacturer(existing.getManufacturer());

        if(null == whiteboard.getSize()
                || "".equals(whiteboard.getSize()))
            whiteboard.setSize(existing.getSize());

        if(0 == whiteboard.getPrice())
            whiteboard.setPrice(existing.getPrice());

        if(0 == whiteboard.getQuantity())
            whiteboard.setQuantity(existing.getQuantity());

        return repository.save(whiteboard);
    }

    @Override
    public Whiteboard createWhiteboard(final Whiteboard whiteboard) {
        return repository.save(whiteboard);
    }

    @Override
    public boolean deleteById(String id) {
        repository.deleteById(id);
        return null == repository.findOne(id);
    }
}
