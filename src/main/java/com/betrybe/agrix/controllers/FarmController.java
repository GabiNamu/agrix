package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.controllers.dto.ResponseCropDto;
import com.betrybe.agrix.exception.CustomError;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FarmController class.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {
  private final FarmService farmService;

  private final CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  @PostMapping()
  public ResponseEntity<Farm> create(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.insert(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * getAll method.
   */
  @GetMapping()
  @Secured({"USER", "MANAGER", "ADMIN"})
  public List<FarmDto> getAll() {
    List<Farm> allFarms = farmService.getAll();
    return allFarms.stream()
      .map((farm) -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
      .collect(Collectors.toList());
  }

  /**
   * getById method.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Farm> getById(@PathVariable Integer id) {
    Optional<Farm> optionalFarm = farmService.getById(id);

    if (optionalFarm.isEmpty()) {
      throw new CustomError("Fazenda não encontrada!", 404);
    }

    return ResponseEntity.ok(optionalFarm.get());
  }

  /**
   * getById method.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<ResponseCropDto> createCrop(
    @PathVariable Integer farmId,
    @RequestBody CropDto cropDto) {
    Optional<Farm> optionalFarm = farmService.getById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new CustomError("Fazenda não encontrada!", 404);
    }

    Crop crop = cropDto.toCrop();
    crop.setFarm(optionalFarm.get());
    cropService.insert(crop);

    return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseCropDto(
      crop.getId(),
      crop.getName(),
      crop.getPlantedArea(),
      crop.getPlantedDate(),
      crop.getHarvestDate(),
      crop.getFarm().getId()));
  }

  /**
   * getCropsByFarmId method.
   */
  @GetMapping("/{farmId}/crops")
  public List<ResponseCropDto> getCropsByFarmId(@PathVariable Integer farmId) {
    Optional<Farm> optionalFarm = farmService.getById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new CustomError("Fazenda não encontrada!", 404);
    }

    List<Crop> allCrops = cropService.getAll();
    return allCrops.stream()
      .filter((crop) -> Objects.equals(crop.getFarm().getId(), farmId))
      .map((crop) -> new ResponseCropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId()))
      .collect(Collectors.toList());
  }
}