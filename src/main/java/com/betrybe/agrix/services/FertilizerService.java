package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FertilizerService class.
 */
@Service
public class FertilizerService {

  FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  public Fertilizer insert(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getAll() {

    return fertilizerRepository.findAll();
  }

  public Optional<Fertilizer> getById(Integer id) {
    return fertilizerRepository.findById(id);
  }

  public List<Fertilizer> getByCrops(Crop crop) {
    return fertilizerRepository.findByCrops(crop);
  }
}