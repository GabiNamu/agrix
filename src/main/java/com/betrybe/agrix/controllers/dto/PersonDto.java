package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * PersonDto record.
 */
public record PersonDto(Long id, String username, String password, Role role) {
  public Person toPerson() {
    return new Person(id, username, password, role);
  }
}
