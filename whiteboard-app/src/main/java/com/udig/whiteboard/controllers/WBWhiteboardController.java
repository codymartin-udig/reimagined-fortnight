package com.udig.whiteboard.controllers;

import com.udig.whiteboard.models.Whiteboard;
import com.udig.whiteboard.models.WhiteboardList;
import com.udig.whiteboard.services.WhiteboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/api/wb")
public class WBWhiteboardController {

    @Autowired
    private WhiteboardService whiteboardService;

    @RequestMapping(value={"", "/"}, method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
    public @ResponseBody WhiteboardList getWhiteboards (@RequestParam(value="token", required = false, defaultValue = "") final String token,
                                   @RequestParam(value="page", required=false, defaultValue = "0") final int page,
                                   @RequestParam(value="size", required = false, defaultValue = "0") final int size) {
        if(page >= 1 && size >= 1)
            return whiteboardService.getWhiteboards(size, page);
        else
            return whiteboardService.getWhiteboards();
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
    public @ResponseBody Whiteboard updateOrCreate (@RequestBody Whiteboard board) {
        System.out.println(board);

        if(board.getId() == null)
            board.setId(generateId());

        System.out.println(board);

        return whiteboardService.updateWhiteboard(board);
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.DELETE, produces = "application/json", headers = "Accept=application/json")
    public @ResponseBody boolean delete (@RequestBody final Whiteboard board) {
        return whiteboardService.deleteById(board.getId());
    }


    @RequestMapping(value="/status", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
    public @ResponseBody boolean status () {
        return true;
    }
}
