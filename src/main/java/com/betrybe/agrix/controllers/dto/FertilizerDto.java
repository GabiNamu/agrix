package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * FertilizerDto record.
 */
public record FertilizerDto(Integer id, String name, String brand, String composition) {

  public Fertilizer toFertilizer() {
    return new Fertilizer(id, name, brand, composition);
  }
}

