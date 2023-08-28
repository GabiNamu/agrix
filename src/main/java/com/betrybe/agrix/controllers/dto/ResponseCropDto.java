package com.betrybe.agrix.controllers.dto;

import java.time.LocalDate;

/**
 * ResponseCropDto interface.
 */
public record ResponseCropDto(
    Integer id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,
    Integer farmId) {
}