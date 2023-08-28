package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.*;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.PersonService;
import com.betrybe.agrix.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
  private final AuthenticationManager authenticationManager;

  private final PersonService personService;

  private final TokenService tokenService;

  @Autowired
  public PersonController(PersonService personService,
                          AuthenticationManager authenticationManager, TokenService tokenService) {
    this.personService = personService;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping("/persons")
  public ResponseEntity<ResponsePersonDto> create(@RequestBody PersonDto personDto) {
    Person newPerson = personService.create(personDto.toPerson());
    return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePersonDto(
      newPerson.getId(),
      newPerson.getUsername(),
      newPerson.getRole()));
  }

  @PostMapping("/auth/login")
  public ResponseEntity<ResponseDto> login(@RequestBody AuthenticationDTO authenticationDTO){

    UsernamePasswordAuthenticationToken usernamePassword =
      new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
    Authentication auth = authenticationManager.authenticate(usernamePassword);

    Person person = (Person) auth.getPrincipal();

    String token = tokenService.generateToken(person);

    ResponseDto response = new ResponseDto(token);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
