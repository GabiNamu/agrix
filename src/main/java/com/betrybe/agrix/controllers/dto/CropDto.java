package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * CropDto record.
 */
public record CropDto(
    Integer id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate) {

  public Crop toCrop() {
    return new Crop(id, name, plantedArea, plantedDate, harvestDate);
  }
}
