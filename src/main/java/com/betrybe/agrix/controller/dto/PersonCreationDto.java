package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * The type Person creation dto.
 */
public record PersonCreationDto(
    String username, String password, Role role) {

  public Person toEntity() {
    return new Person(username, password, role);
  }
}
