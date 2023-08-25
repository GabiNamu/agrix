package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.security.Role;

public record ResponsePersonDto(Long id, String username, Role role) {
}
