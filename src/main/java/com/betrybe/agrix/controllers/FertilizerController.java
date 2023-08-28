package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.exception.CustomError;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
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
 * FertilizerController class.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {

  FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  @PostMapping()
  public ResponseEntity<Fertilizer> create(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer newFertilizer = fertilizerService.insert(fertilizerDto.toFertilizer());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFertilizer);
  }

  /**
   * getAll method.
   */
  @GetMapping()
  @Secured("ADMIN")
  public List<FertilizerDto> getAll() {
    List<Fertilizer> allFertilizers = fertilizerService.getAll();
    return allFertilizers.stream()
      .map((fertilizer) -> new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()))
      .collect(Collectors.toList());
  }

  /**
   * getById method.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Fertilizer> getById(@PathVariable Integer id) {
    Optional<Fertilizer> optionalFertilizer = fertilizerService.getById(id);

    if (optionalFertilizer.isEmpty()) {
      throw new CustomError("Fertilizante não encontrado!", 404);
    }

    return ResponseEntity.ok(optionalFertilizer.get());
  }
}

