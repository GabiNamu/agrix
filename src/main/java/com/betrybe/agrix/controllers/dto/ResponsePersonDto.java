package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.security.Role;

/**
 * ResponsePersonDto record.
 */
public record ResponsePersonDto(Long id, String username, Role role) {
}
