package com.anthonypoon.loginbackend.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/testing")
    public ResponseEntity<Void> doTesting() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
