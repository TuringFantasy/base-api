package honeybadger;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HoneybadgerController {

    @RequestMapping("/")
    public String index() {
        return "test route";
    }

}