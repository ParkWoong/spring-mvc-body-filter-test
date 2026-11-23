package io.github.parkwoong.example;

import org.springframework.web.bind.annotation.*;

@RestController
public class EchoController {
    @PostMapping("/echo")
    public String echo(@RequestBody String body) {
        return body;
    }
}
