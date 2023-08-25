package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CropRepository interface.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Integer> {
}