package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {
  private final CropService cropService;
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  @Autowired
  public CropController(CropService cropService, FertilizerService fertilizerService) {
    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{id}")
  public CropDto getById(@PathVariable Long id) throws CropNotFoundException {
    return CropDto.fromEntity(cropService.findById(id));
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  public List<CropDto> getAllCrops() {
    return cropService.findAll().stream().map(CropDto::fromEntity).toList();
  }

  /**
   * Gets by harvest date.
   *
   * @param start the start
   * @param end   the end
   * @return the by harvest date
   */
  @GetMapping("/search")
  public List<CropDto> getByHarvestDate(@RequestParam LocalDate start,
      @RequestParam LocalDate end) {

    return cropService.findByHarvestDateBetween(start, end)
        .stream().map(CropDto::fromEntity).toList();
  }

  @PostMapping("{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public String linkCropToFarm(@PathVariable Long cropId, @PathVariable Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    return fertilizerService.linkFertilizerToCrop(cropId, fertilizerId);
  }

  @GetMapping("/{id}/fertilizers")
  public List<FertilizerDto> getFertilizersByCropId(@PathVariable Long id)
      throws CropNotFoundException {
    List<Fertilizer> fertilizers = fertilizerService.findFertilizerByCropId(id);
    return fertilizers.stream().map(FertilizerDto::fromEntity).toList();
  }
}
