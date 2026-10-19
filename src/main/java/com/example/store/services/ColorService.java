package com.example.store.services;

import com.example.store.dtos.ColorRequest;
import com.example.store.exceptions.ResourceNotFoundException;
import com.example.store.models.Color;
import com.example.store.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {

    private final ColorRepository colorRepository;

    @Autowired
    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    public Color getColorById(Long id) {
        return colorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Color con el id " + id + " no encontrado")
        );
    }

    public Color createColor(ColorRequest newColor) {
        Color color = new Color();
        color.setColorName(newColor.colorName());
        return colorRepository.save(color);
    }

    public Color updateColor(Long id, ColorRequest updateColor) {
        Color oldColor = colorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Color con el id " + id + " no encontrado")
        );
        if (updateColor.colorName() != null) {
            oldColor.setColorName(updateColor.colorName());
        }
        return colorRepository.save(oldColor);
    }

    public Color deleteColorById(Long id) {
        Color color = colorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Color con el id " + id + " no encontrado")
        );
        colorRepository.delete(color);
        return color;
    }
}