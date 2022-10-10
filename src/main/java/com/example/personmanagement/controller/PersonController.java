package com.example.personmanagement.controller;

import com.example.personmanagement.api.PDFService;
import com.example.personmanagement.api.service.PersonService;
import com.example.personmanagement.dto.PersonDto;
import com.example.personmanagement.services.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String HEADER_VALUE = "headerValuePdf";
    private static final String APPLICATION_PDF = "application/pdf";
    private final MessageService messageService;
    private final PersonService personService;
    private final PDFService pdfService;


    @GetMapping("/{uid}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("uid") String uid) {
        log.info("Was calling getPerson. Input uid: {}", uid);
        return ResponseEntity.ok(personService.getPersonByUid(uid));
    }

    @GetMapping()
    public ResponseEntity<List<PersonDto>> getPersons() {
        log.info("Was calling getPersons.");
        return ResponseEntity.ok(personService.getPersons());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePerson(@PathVariable("id") Long id) {
        log.info("Was calling deletePerson. Input id: {}", id);
        return ResponseEntity.ok(personService.deletePerson(id));
    }

    @PostMapping("/add")
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto) {
        log.info("Was calling createPerson. Input person: {}", personDto);
        return ResponseEntity.ok(personService.addNewPerson(personDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable("id") Long id, @RequestBody PersonDto personDto) {
        log.info("Was calling updatePerson. Input id: {} person: {}", id, personDto);
        return ResponseEntity.ok(personService.updatePerson(id, personDto));
    }

    @PostMapping()
    public ResponseEntity<List<PersonDto>> createPersons(@RequestBody List<PersonDto> personsDto) {
        log.info("Was calling createPersons.Input persons: {}", personsDto);
        return ResponseEntity.ok(personService.addNewPersons(personsDto));
    }

    @GetMapping("/{uid}/pdf")
    public void getPersonPdf(@PathVariable("uid") String uid, HttpServletResponse response) throws IOException {
        log.info("Was calling getPersonPdf. Input uid: {}", uid);

        response.setContentType(APPLICATION_PDF);// todo вынести логику из контроллера
        PersonDto personDto = personService.getPersonByUid(uid);
        response.setHeader(CONTENT_DISPOSITION,
                MessageFormat.format(messageService.getMessage(HEADER_VALUE), personDto.getName()));
        pdfService.export(response, personDto);
    }
}
