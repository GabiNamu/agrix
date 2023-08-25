package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FertilizerRepository interface.
 */
public interface FertilizerRepository extends JpaRepository<Fertilizer, Integer> {
  List<Fertilizer> findByCrops(Crop crop);
}