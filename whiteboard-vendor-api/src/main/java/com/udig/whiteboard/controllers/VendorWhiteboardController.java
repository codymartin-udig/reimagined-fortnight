package com.udig.whiteboard.controllers;

import com.udig.whiteboard.models.Whiteboard;
import com.udig.whiteboard.models.WhiteboardList;
import com.udig.whiteboard.services.WhiteboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/vendor")
public class VendorWhiteboardController {
    Logger LOGGER = LoggerFactory.getLogger(VendorWhiteboardController.class);
    @Autowired
    private WhiteboardService whiteboardService;

    private final static String PERM_KEY = "0942175430";

    /*
     * For the sake of simplistic code, vendor keys aren't implemented beyond a single comparison.
     */
    private static boolean verifyVendorKey(final String hash) {
        return hash.equals(PERM_KEY);
    }

    @RequestMapping(value={"", "/"}, method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
    public @ResponseBody WhiteboardList getWhiteboards (@RequestParam(value="token", required = false, defaultValue = "") final String token,
                                                        @RequestParam(value="page", required=false, defaultValue = "0") final int page,
                                                        @RequestParam(value="size", required = false, defaultValue = "0") final int size) {
        if(!verifyVendorKey(token))
            return new WhiteboardList();

        if(page >= 1 && size >= 1)
            return whiteboardService.getWhiteboards(size, page);
        else
            return whiteboardService.getWhiteboards();
    }


    @RequestMapping(value="/order", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
    public @ResponseBody Whiteboard order (@RequestParam(value = "token", required=false, defaultValue = "") final String token,
                                            @RequestParam("id") final String id,
                                            @RequestParam(value = "quantity", required = false, defaultValue = "1") final int quantity) {
        if(!verifyVendorKey(token))
            return new Whiteboard();

        final Whiteboard product = whiteboardService.getWhiteboardById(id);
        if(product.getQuantity() < quantity)
            return product;

        product.setSold(quantity);
        return whiteboardService.updateWhiteboard(product);
    }

    @RequestMapping(value="/crash", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
    public void purposefulAppKiller (@RequestParam(value="token", required = false, defaultValue = "") final String token) {
        if(verifyVendorKey(token))
            System.exit(0);
    }

    @RequestMapping(value="/status", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
    public @ResponseBody boolean status () {
        return true;
    }
}
