package com.example.store.controllers;

import com.example.store.dtos.DescriptionRequest;
import com.example.store.models.Description;
import com.example.store.services.DescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/description") // http://localhost:8080/api/description
@CrossOrigin(origins = "*")
public class DescriptionController {

    private final DescriptionService descriptionService;

    @Autowired
    public DescriptionController(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    @GetMapping
    public ResponseEntity<List<Description>> getAllDescriptions() {
        List<Description> descriptions = descriptionService.getAllDescriptions();
        return ResponseEntity.ok(descriptions);
    }

    @GetMapping(path = "{descriptionId}")
    public ResponseEntity<Description> getDescriptionById(@PathVariable("descriptionId") Long id) {
        Description description = descriptionService.getDescriptionById(id);
        return ResponseEntity.ok(description);
    }

    @PostMapping
    public ResponseEntity<Description> createDescription(@Valid @RequestBody DescriptionRequest descriptionRequest) {
        Description description = descriptionService.createDescription(descriptionRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(description.getId())
                .toUri();
        return ResponseEntity.created(location).body(description);
    }

    @PutMapping(path = "{descriptionId}")
    public ResponseEntity<Description> updateDescriptionById(
            @PathVariable("descriptionId") Long id,
            @RequestBody DescriptionRequest descriptionRequest) {
        Description description = descriptionService.updateDescription(id, descriptionRequest);
        return ResponseEntity.ok(description);
    }

    @DeleteMapping(path = "{descriptionId}")
    public ResponseEntity<Description> deleteDescriptionById(@PathVariable("descriptionId") Long id) {
        Description description = descriptionService.deleteDescriptionById(id);
        return ResponseEntity.ok(description);
    }
}