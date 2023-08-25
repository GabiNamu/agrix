package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Farm;

/**
 * FarmDTO record.
 */
public record FarmDto(Integer id, String name, Double size) {

  public Farm toFarm() {
    return new Farm(id, name, size, null);
  }
}
