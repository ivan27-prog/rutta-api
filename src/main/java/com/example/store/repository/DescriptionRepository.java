package com.example.store.repository;

import com.example.store.models.Category;
import com.example.store.models.Description;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DescriptionRepository extends JpaRepository<Description, Long>{
List<Category> findByProductId(Long productId);
}
