package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.controllers.dto.ResponseCropDto;
import com.betrybe.agrix.exception.CustomError;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FertilizerService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * CropController class.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {
  private final CropService cropService;

  private final FertilizerService fertilizerService;

  /**
   * CropController constructor.
   */
  @Autowired
  public CropController(CropService cropService, FertilizerService fertilizerService) {

    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * getAll method.
   */
  @GetMapping()
  public List<ResponseCropDto> getAll() {
    List<Crop> allCrops = cropService.getAll();
    return allCrops.stream()
      .map((crop) -> new ResponseCropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId()))
      .collect(Collectors.toList());
  }

  /**
   * getById method.
   */
  @GetMapping("/{id}")
  public ResponseEntity<ResponseCropDto> getById(@PathVariable Integer id) {
    Optional<Crop> optionalCrop = cropService.getById(id);

    if (optionalCrop.isEmpty()) {
      throw new CustomError("Plantação não encontrada!", 404);
    }

    Crop crop = optionalCrop.get();
    return ResponseEntity.ok(new ResponseCropDto(
      crop.getId(),
      crop.getName(),
      crop.getPlantedArea(),
      crop.getPlantedDate(),
      crop.getHarvestDate(),
      crop.getFarm().getId()));
  }

  /**
   * getByHarvestDate method.
   */
  @GetMapping("/search")
  public List<ResponseCropDto> getByHarvestDate(
    @RequestParam LocalDate start,
    @RequestParam LocalDate end) {
    List<Crop> crops = cropService.getAll();
    return crops.stream()
      .filter((crop) ->
        !(crop.getHarvestDate().isAfter(end)
          || crop.getHarvestDate().isBefore(start)))
      .map((crop) -> new ResponseCropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId()))
      .collect(Collectors.toList());
  }

  /**
   * setFertilizer method.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> setFertilizer(
    @PathVariable Integer cropId,
    @PathVariable Integer fertilizerId) {
    cropService.setFertilizer(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
      .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * getFertilizersByCropId class.
   */
  @GetMapping("/{cropId}/fertilizers")
  public List<FertilizerDto> getFertilizersByCropId(@PathVariable Integer cropId) {
    Optional<Crop> optionalCrop = cropService.getById(cropId);

    if (optionalCrop.isEmpty()) {
      throw new CustomError("Plantação não encontrada!", 404);
    }

    Crop crop = optionalCrop.get();
    List<Fertilizer> fertilizers = fertilizerService.getByCrops(crop);
    return fertilizers.stream()
      .map((fertilizer) -> new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()))
      .collect(Collectors.toList());
  }
}

