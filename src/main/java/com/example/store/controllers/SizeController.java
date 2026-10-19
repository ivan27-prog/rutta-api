package com.example.store.controllers;

import com.example.store.dtos.SizeRequest;
import com.example.store.models.Size;
import com.example.store.services.SizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/size") // http://localhost:8080/api/size
@CrossOrigin(origins = "*")
public class SizeController {

    private final SizeService sizeService;

    @Autowired
    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping
    public ResponseEntity<List<Size>> getAllSizes() {
        List<Size> sizes = sizeService.getAllSizes();
        return ResponseEntity.ok(sizes);
    }

    @GetMapping(path = "{sizeId}")
    public ResponseEntity<Size> getSizeById(@PathVariable("sizeId") Long id) {
        Size size = sizeService.getSizeById(id);
        return ResponseEntity.ok(size);
    }

    @PostMapping
    public ResponseEntity<Size> createSize(@Valid @RequestBody SizeRequest sizeRequest) {
        Size size = sizeService.createSize(sizeRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(size.getId())
                .toUri();
        return ResponseEntity.created(location).body(size);
    }

    @PutMapping(path = "{sizeId}")
    public ResponseEntity<Size> updateSizeById(
            @PathVariable("sizeId") Long id,
            @RequestBody SizeRequest sizeRequest) {
        Size size = sizeService.updateSize(id, sizeRequest);
        return ResponseEntity.ok(size);
    }

    @DeleteMapping(path = "{sizeId}")
    public ResponseEntity<Size> deleteSizeById(@PathVariable("sizeId") Long id) {
        Size size = sizeService.deleteSizeById(id);
        return ResponseEntity.ok(size);
    }
}