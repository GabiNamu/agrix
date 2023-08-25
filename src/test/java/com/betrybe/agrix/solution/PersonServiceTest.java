package com.betrybe.agrix.solution;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.exception.PersonNotFoundException;
import com.betrybe.agrix.security.Role;
import com.betrybe.agrix.services.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Testa PersonService class")
public class PersonServiceTest {
  @Autowired
  PersonService personService;

  @Test
  public void testGetPersonById() {
    Person person = new Person();
    person.setUsername("test_test01");
    person.setPassword("test1234");
    person.setRole(Role.USER);

    Long createdId = personService.create(person).getId();

    Person returnedPerson = personService.getPersonById(createdId);

    assertEquals(returnedPerson.getId(), createdId);
    assertEquals(returnedPerson.getUsername(), person.getUsername());
    assertEquals(returnedPerson.getPassword(), person.getPassword());
    assertEquals(returnedPerson.getRole(), person.getRole());
  }

  @Test
  public void testGetPersonByIdNotFound() {
    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(9999L));
  }

  @Test
  public void testGetPersonByUsername() {
    Person person = new Person();
    person.setUsername("test_test02");
    person.setPassword("test1234");
    person.setRole(Role.USER);

    String createdUsername = personService.create(person).getUsername();

    Person returnedPerson = personService.getPersonByUsername(createdUsername);

    assertEquals(returnedPerson.getUsername(), createdUsername);
    assertEquals(returnedPerson.getPassword(), person.getPassword());
    assertEquals(returnedPerson.getRole(), person.getRole());
  }

  @Test
  public void testGetPersonByUsernameNotFound() {
    assertThrows(PersonNotFoundException.class, () -> personService.getPersonByUsername("xxxxxxx"));
  }

  @Test
  public void testPersonCreation() {
    Person person = new Person();
    person.setUsername("test_test03");
    person.setPassword("test1234");
    person.setRole(Role.ADMIN);

    Person savedPerson = personService.create(person);

    assertNotNull(savedPerson.getId());
    assertEquals(person.getUsername(), savedPerson.getUsername());
    assertEquals(person.getPassword(), savedPerson.getPassword());
    assertEquals(person.getRole(), savedPerson.getRole());
  }
}
