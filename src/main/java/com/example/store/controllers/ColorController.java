package com.example.store.controllers;

import com.example.store.dtos.ColorRequest;
import com.example.store.models.Color;
import com.example.store.services.ColorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/color") // http://localhost:8080/api/color
@CrossOrigin(origins = "*")
public class ColorController {

    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public ResponseEntity<List<Color>> getAllColors() {
        List<Color> colors = colorService.getAllColors();
        return ResponseEntity.ok(colors);
    }

    @GetMapping(path = "{colorId}")
    public ResponseEntity<Color> getColorById(@PathVariable("colorId") Long id) {
        Color color = colorService.getColorById(id);
        return ResponseEntity.ok(color);
    }

    @PostMapping
    public ResponseEntity<Color> createColor(@Valid @RequestBody ColorRequest colorRequest) {
        Color color = colorService.createColor(colorRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(color.getId())
                .toUri();
        return ResponseEntity.created(location).body(color);
    }

    @PutMapping(path = "{colorId}")
    public ResponseEntity<Color> updateColorById(
            @PathVariable("colorId") Long id,
            @RequestBody ColorRequest colorRequest) {
        Color color = colorService.updateColor(id, colorRequest);
        return ResponseEntity.ok(color);
    }

    @DeleteMapping(path = "{colorId}")
    public ResponseEntity<Color> deleteColorById(@PathVariable("colorId") Long id) {
        Color color = colorService.deleteColorById(id);
        return ResponseEntity.ok(color);
    }
}

