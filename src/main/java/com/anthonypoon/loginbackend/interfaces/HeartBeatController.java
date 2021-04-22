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