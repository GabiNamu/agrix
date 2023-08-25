package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.controllers.dto.PersonDto;
import com.betrybe.agrix.controllers.dto.ResponsePersonDto;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {
  private PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping()
  public ResponseEntity<ResponsePersonDto> create(@RequestBody PersonDto personDto) {
    Person person = new Person();
    person.setUsername(personDto.username());
    person.setPassword(personDto.password());
    person.setRole(personDto.role());
    Person newPerson = personService.create(person);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePersonDto(
      newPerson.getId(),
      newPerson.getUsername(),
      newPerson.getRole()));
  }
}
