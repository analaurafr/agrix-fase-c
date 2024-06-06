package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.FertilizerRepository;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Fertilizer service.
 */
@Service
public class FertilizerService {
  private final FertilizerRepository fertilizerRepository;
  private final CropService cropService;


  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository, CropService cropService) {
    this.fertilizerRepository = fertilizerRepository;
    this.cropService = cropService;
  }

  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> findAll() {
    return fertilizerRepository.findAll();
  }

  public Fertilizer findById(Long id) throws FertilizerNotFoundException {
    return fertilizerRepository.findById(id).orElseThrow(FertilizerNotFoundException::new);
  }

  /**
   * Link fertilizer to crop string.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the string
   * @throws FertilizerNotFoundException the fertilizer not found exception
   * @throws CropNotFoundException       the crop not found exception
   */
  @Transactional
  public String linkFertilizerToCrop(Long cropId, Long fertilizerId)
      throws FertilizerNotFoundException, CropNotFoundException {
    Fertilizer fertilizer = findById(fertilizerId);
    Crop crop = cropService.findById(cropId);

    fertilizer.getCrops().add(crop);

    return "Fertilizante e plantação associados com sucesso!";
  }

  public List<Fertilizer> findFertilizerByCropId(Long cropId) throws CropNotFoundException {
    Crop crop = cropService.findById(cropId);
    return crop.getFertilizers();
  }
}
