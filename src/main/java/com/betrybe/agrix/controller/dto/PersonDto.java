package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * The type Person dto.
 */
public record PersonDto(
    Long id, String username, Role role
) {

  public static PersonDto fromEntity(Person person) {
    return new PersonDto(person.getId(), person.getUsername(), person.getRole());
  }
}
