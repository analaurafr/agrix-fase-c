package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Fertilizer;

/**
 * The type Fertilizer creation dto.
 */
public record FertilizerCreationDto(String name, String brand, String composition) {
  public Fertilizer toEntity() {
    return new Fertilizer(name, brand, composition);
  }
}
