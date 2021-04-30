package com.anthonypoon.loginbackend.interfaces;

import com.anthonypoon.loginbackend.dto.HeartBeatResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartBeatController {
    @GetMapping("/heart-beat")
    public HeartBeatResponseDTO getHeartBeat() {
        return new HeartBeatResponseDTO();
    }
}

// Step 1:  Download boiler plate from Spring initializer
//          - Add JPA, Spring Web, Lombok as dependency
// Step 2:  Create package interfaces, then create class HeartBeatController
// Step 3:  Create package dto, then create class HeartBeatResponseDTO
// Step 4:  Annotate HeartBeatController with @RestController and method with @GetMapping

// HeartBeatResponseDTO is just for demo purpose so that response body have content

// @GetMapping maps that url to the controller method
// - @GetMapping can inject path variable, i.e. "/heart-beat/{id}" -> getHeartBeat(@PathVariable String id)
// - Or inject params, i.e. "/heart-beat?id=123" -> getHeartBeat(@RequestParam String id)

// Whats the difference between @RestController vs Controller?
// - @RestController = @Controller + @ResponseBody
// - @ResponseBody will tell the controller that the returned object should be encoded into JSON.
// - Without @ResponseBody, you need to return a String, ModalAneView, or and ResponseEntity / HttpResponse