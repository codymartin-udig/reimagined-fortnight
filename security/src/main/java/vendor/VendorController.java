package vendor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class VendorController {

    @RequestMapping("/vendor")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}