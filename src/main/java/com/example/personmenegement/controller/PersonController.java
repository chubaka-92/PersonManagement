package com.example.personmenegement.controller;

import com.example.personmenegement.api.PersonService;
import com.example.personmenegement.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/person")// todo лучше написать в единственном числе //  DONE
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{uid}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("uid") String uid) { // todo ResponseEntity без параметризации выглядит некрасиво, лушче сделать ResponseEntity<Long> или для простоты ResponseEntity<?>  //   DONE
        log.info("Was calling getPerson. Input uid: {}", uid);
        return ResponseEntity.ok(personService.getPersonByUid(uid)); // todo зачем valueOf если и так приходит Long? убрать // DONE
    }

    @GetMapping()
    public ResponseEntity<List<PersonDto>> getPersons() {// todo ResponseEntity смотри выше //  DONE
        log.info("Was calling getPersons.");
        return ResponseEntity.ok(personService.getPersons());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePerson(@PathVariable("id") Long id) { // todo ResponseEntity смотри выше //  DONE
        log.info("Was calling deletePerson. Input id: {}", id);
        return ResponseEntity.ok(personService.deletePerson(id));// todo зачем valueOf если и так приходит Long? убрать  // DONE
    }

    @PostMapping("/add")
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto) {// todo ResponseEntity смотри выше //  DONE
        log.info("Was calling createPerson. Input person: {}", personDto.toString());
        return ResponseEntity.ok(personService.addNewPerson(personDto));
    }

    @PutMapping()
    public ResponseEntity<PersonDto> updatePerson(@RequestBody PersonDto personDto) {// todo ResponseEntity смотри выше //  DONE
        log.info("Was calling updatePerson. Input person: {}", personDto);
        return ResponseEntity.ok(personService.updatePerson(personDto));
    }

    @PostMapping()
    public ResponseEntity<List<PersonDto>> createPersons(@RequestBody List<PersonDto> personsDto) {// todo ResponseEntity смотри выше //  DONE
        log.info("Was calling createPersons.Input persons: {}", personsDto);
        return ResponseEntity.ok(personService.addNewPersons(personsDto));
    }
}
