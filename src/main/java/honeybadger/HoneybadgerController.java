package honeybadger;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class HoneybadgerController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "test route";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
    	return "test";
    }

}