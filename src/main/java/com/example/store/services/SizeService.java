package com.example.store.services;

import com.example.store.dtos.SizeRequest;
import com.example.store.exceptions.ResourceNotFoundException;
import com.example.store.models.Size;
import com.example.store.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {

    private final SizeRepository sizeRepository;

    @Autowired
    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public List<Size> getAllSizes() {
        return sizeRepository.findAll();
    }

    public Size getSizeById(Long id) {
        return sizeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Talla con el id " + id + " no encontrada")
        );
    }

    public Size createSize(SizeRequest newSize) {
        Size size = new Size();
        size.setSizeValue(newSize.sizeValue());
        return sizeRepository.save(size);
    }

    public Size updateSize(Long id, SizeRequest updateSize) {
        Size oldSize = sizeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Talla con el id " + id + " no encontrada")
        );
        if (updateSize.sizeValue() != null) {
            oldSize.setSizeValue(updateSize.sizeValue());
        }
        return sizeRepository.save(oldSize);
    }

    public Size deleteSizeById(Long id) {
        Size size = sizeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Talla con el id " + id + " no encontrada")
        );
        sizeRepository.delete(size);
        return size;
    }
}