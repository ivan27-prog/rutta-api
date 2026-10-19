package com.example.store.controllers;


import com.example.store.dtos.SizeRequest;
import com.example.store.models.Size;
import com.example.store.services.SizeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/size")
@AllArgsConstructor
public class SizeController {

    private final SizeService sizeService;

    // Petición Get todos los tamaños
    @GetMapping
    public List<Size> getAllSizes() {
        return sizeService.getAllSizes();
    }

    // Petición Get con un ID
    @GetMapping(path = "/{sizeId}")
    public Size getSizeById(@PathVariable("sizeId") Long sizeId) {
        return sizeService.getSizeById(sizeId);
    }

    // Peticion Post para crear una nueva talla
    @PostMapping
    public ResponseEntity<Size> createSize(
            @Valid @RequestBody SizeRequest sizeRequest)  {
        Size size = sizeService.createSize(sizeRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(size.getId())
                .toUri();
        return ResponseEntity.created(location).body(size);

    }

    // Petición Put para actualizar una talla existente
        @PutMapping(path = "/{sizeId}")
        public ResponseEntity<Size> updateSize(
                @PathVariable("sizeId") Long sizeId,
                @Valid @RequestBody SizeRequest sizeRequest) {
        Size size = sizeService.updateSize(sizeId, sizeRequest);
        return ResponseEntity.ok(size);
    }

    // Petición Delete para eliminar una talla existente
    @DeleteMapping(path = "/{sizeId}")
    public ResponseEntity<Void> deleteSize(@PathVariable("sizeId") Long sizeId){
        sizeService.deleteSize(sizeId);
        return ResponseEntity.noContent().build();
    }


}
