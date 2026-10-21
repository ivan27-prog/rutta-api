/*package com.example.store.repository;

import com.example.store.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// @Repository: componente de acceso a datos
@Repository
// JpaRepository<Entidad, TipoId> ya incluye el CRUD: save, findAll, findById, existsById, count, deleteById, delete
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}


 */