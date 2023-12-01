package com.fooddelivery.sagaservice.controller;

import com.fooddelivery.sagaservice.service.SagaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/saga")
public class SagaController {
    private final SagaService sagaService;

    public SagaController(SagaService sagaService) {
        this.sagaService = sagaService;
    }

    @PostMapping(path = "/compensate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> initiateCompensation(@RequestBody Map<String, Long> requestBody) {
        Long orderId = requestBody.get("orderId");
        sagaService.initiateCompensation(orderId);
        return ResponseEntity.ok().build();
    }

}
