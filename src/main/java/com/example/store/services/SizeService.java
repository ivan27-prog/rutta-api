package com.example.store.services;


import com.example.store.dtos.SizeRequest;
import com.example.store.exceptions.ResourceNotFoundException;
import com.example.store.models.Size;
import com.example.store.repository.SizeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class SizeService {
    //Permite acceder a las tallas guardadas en la base de datos.
    private final SizeRepository sizeRepository;

    //Metodo para obtener todas las tallas
    public List<Size> getAllSizes() {
        return sizeRepository.findAll();
    }

    //Metodo para buscar una talla por su id
    public Size getSizeById(long sizeId) {
        return sizeRepository.findById(sizeId).orElseThrow(() -> new ResourceNotFoundException("Talla con id " + sizeId + " no encontrada"));
    }

    //Metodo para crear una talla
    public Size createSize(SizeRequest sizeRequest) {
        Size size = new Size();
        size.setSizeValue(sizeRequest.sizeValue());
        return sizeRepository.save(size);
    }

    //Metodo para actualizar una talla
    public Size updateSize(long sizeId, SizeRequest sizeRequest) {
        Size size = getSizeById(sizeId);
        if (sizeRequest.sizeValue() != null) {
            size.setSizeValue(sizeRequest.sizeValue());
        }
        return sizeRepository.save(size);
    }

    //Metodo para eliminar una talla
    public Size deleteSize(long sizeId) {
        Size size = getSizeById(sizeId);
        sizeRepository.delete(size);
        return size;
    }
}
