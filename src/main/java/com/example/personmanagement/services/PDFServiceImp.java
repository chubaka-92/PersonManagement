package com.example.personmanagement.services;

import com.example.personmanagement.api.PDFService;
import com.example.personmanagement.dto.PersonDto;
import com.example.personmanagement.dto.TaskDto;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.librepdf.openpdf.fonts.Liberation;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

@Slf4j
@Service
@RequiredArgsConstructor
public class PDFServiceImp implements PDFService {

    private static final String LOGO = "src\\main\\resources\\cat.jpg";
    private static final String PERSON = "person";
    private static final String PERSON_ID = "personId";
    private static final String PERSON_UID = "personUid";
    private static final String PERSON_NAME = "personName";
    private static final String PERSON_AGE = "personAge";
    private static final String PERSON_EMAIL = "personEmail";
    private static final String PERSON_POSITION = "personPosition";
    private static final String PERSON_SALARY = "personSalary";
    private static final String PERSON_EXPERIENCE = "personWorkExperience";
    private static final String TASK_TITLE = "tasksTitle";
    private static final String TASK_ID = "taskId";
    private static final String TASK_UID = "taskUid";
    private static final String TASK_DESCRIPTION = "taskDescription";
    private static final String TASK_PRIORITY = "taskPriority";
    private final MessageService messageService;

    public void export(HttpServletResponse response, PersonDto personDto) throws IOException {
        log.info("Was calling export. Input personDto: {}", personDto);
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = Liberation.SANS_BOLD.create();
        fontTitle.setSize(20);
        Font fontTitleTask = Liberation.SANS_BOLD.create();
        fontTitle.setSize(15);
        Font fontDescription = Liberation.SANS.create();
        fontDescription.setSize(12);

        setPerson(personDto, document, fontTitle, fontDescription);
        setTasks(personDto, document, fontTitleTask, fontDescription);
        setLogo(document);

        document.close();
    }

    private void setPerson(PersonDto personDto, Document document, Font fontTitle, Font fontDescription) {
        log.debug("Was calling setPerson.");
        Paragraph titlePerson = new Paragraph(getFormat(PERSON, personDto.getName()), fontTitle);
        Paragraph idPerson = new Paragraph(getFormat(PERSON_ID, personDto.getId()), fontDescription);
        Paragraph uidPerson = new Paragraph(getFormat(PERSON_UID, personDto.getUid()), fontDescription);
        Paragraph namePerson = new Paragraph(getFormat(PERSON_NAME, personDto.getName()), fontDescription);
        Paragraph agePerson = new Paragraph(getFormat(PERSON_AGE, personDto.getAge()), fontDescription);
        Paragraph emailPerson = new Paragraph(getFormat(PERSON_EMAIL, personDto.getEmail()), fontDescription);
        Paragraph positionPerson = new Paragraph(getFormat(PERSON_POSITION, personDto.getPosition()), fontDescription);
        Paragraph salaryPerson = new Paragraph(getFormat(PERSON_SALARY, personDto.getSalary()), fontDescription);
        Paragraph experiencePerson = new Paragraph(getFormat(PERSON_EXPERIENCE, personDto.getExperience()), fontDescription);

        document.add(titlePerson);
        document.add(idPerson);
        document.add(uidPerson);
        document.add(namePerson);
        document.add(agePerson);
        document.add(emailPerson);
        document.add(positionPerson);
        document.add(salaryPerson);
        document.add(experiencePerson);
    }

    private void setLogo(Document document) throws IOException {
        log.debug("Was calling setLogo.");
        Image img = Image.getInstance(LOGO);
        img.setAbsolutePosition(350, 580);
        document.add(img);
    }

    private void setTasks(PersonDto personDto, Document document, Font fontTitleTask, Font fontDescription) {
        log.debug("Was calling setTasks.");
        if (!personDto.getTasks().isEmpty()) {
            Paragraph titleTask = new Paragraph(messageService.getMessage(TASK_TITLE), fontTitleTask);
            document.add(Chunk.NEWLINE);
            document.add(titleTask);
            for (TaskDto task : personDto.getTasks()) {
                setTask(document, fontDescription, task);
            }
        }
    }

    private void setTask(Document document, Font fontDescription, TaskDto task) {
        log.debug("Was calling setTask. Input task: {}", task);
        Paragraph idTask = new Paragraph(getFormat(TASK_ID, task.getId()), fontDescription);
        Paragraph uidTask = new Paragraph(getFormat(TASK_UID, task.getUid()), fontDescription);
        Paragraph descriptionTask = new Paragraph(getFormat(TASK_DESCRIPTION, task.getDescription()), fontDescription);
        Paragraph priorityTask = new Paragraph(getFormat(TASK_PRIORITY, task.getPriority()), fontDescription);

        document.add(idTask);
        document.add(uidTask);
        document.add(descriptionTask);
        document.add(priorityTask);
        document.add(Chunk.NEWLINE);
    }

    private String getFormat(String constant, String param) {
        return MessageFormat.format(messageService.getMessage(constant), param);
    }
}
