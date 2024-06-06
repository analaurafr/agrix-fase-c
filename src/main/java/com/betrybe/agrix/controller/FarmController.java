package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropCreationDto;
import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {
  private final FarmService farmService;
  private final CropService cropService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   * @param cropService the crop service
   */
  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createFarm(@RequestBody FarmCreationDto farmCreationDto) {
    return FarmDto.fromEntity(farmService.createFarm(farmCreationDto.toEntity()));
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping
  public List<FarmDto> getAllFarms() {
    return farmService.findAllFarms().stream().map(FarmDto::fromEntity).toList();
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping(value = "/{id}")
  public FarmDto getFarmById(@PathVariable Long id) throws FarmNotFoundException {
    return FarmDto.fromEntity(farmService.findById(id));
  }

  /**
   * Create crop crop dto.
   *
   * @param farmId          the farm id
   * @param cropCreationDto the crop creation dto
   * @return the crop dto
   */
  @PostMapping(value = "/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto createCrop(@PathVariable Long farmId,
      @RequestBody CropCreationDto cropCreationDto) throws FarmNotFoundException {
    return CropDto.fromEntity(cropService.createCrop(farmId, cropCreationDto.toEntity()));
  }


  /**
   * Gets all crops by farm id.
   *
   * @param farmId the farm id
   * @return the all crops by farm id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping(value = "/{farmId}/crops")
  public List<CropDto> getAllCropsByFarmId(@PathVariable Long farmId) throws FarmNotFoundException {
    List<Crop> farm = farmService.findById(farmId).getCrops();

    return farm.stream().map(CropDto::fromEntity).toList();
  }
}
