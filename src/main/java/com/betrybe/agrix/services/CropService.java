package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.CustomError;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CropServicee class.
 */
@Service
public class CropService {
  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  public List<Crop> getAll() {
    return cropRepository.findAll();
  }

  public Crop insert(Crop crop) {
    return cropRepository.save(crop);
  }

  public Optional<Crop> getById(Integer id) {
    return cropRepository.findById(id);
  }

  /**
   * setFertilizer method.
   */
  public void setFertilizer(Integer cropId, Integer fertilizerId) {
    Optional<Crop> dbCrop = cropRepository.findById(cropId);
    if (dbCrop.isEmpty()) {
      throw new CustomError("Plantação não encontrada!", 404);
    }

    Optional<Fertilizer> dbFertilizer = fertilizerRepository.findById(fertilizerId);
    if (dbFertilizer.isEmpty()) {
      throw new CustomError("Fertilizante não encontrado!", 404);
    }

    Crop crop = dbCrop.get();
    Fertilizer fertilizer = dbFertilizer.get();

    crop.getFertilizers().add(fertilizer);
    fertilizer.getCrops().add(crop);

    fertilizerRepository.save(fertilizer);
    cropRepository.save(crop);
  }

}