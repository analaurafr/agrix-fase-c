package com.betrybe.agrix.service.exception;

/**
 * The type Fertilizer not found exception.
 */
public class FertilizerNotFoundException extends NotFoundException {
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }
}