package com.example.personmenegement.types;

import com.example.personmenegement.api.MessageService;
import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.services.MessageServiceImp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Getter
@ToString
@AllArgsConstructor
public enum Position {

    INTERN(BigDecimal.valueOf(15000), BigDecimal.valueOf(20000), 0.0, 1),
    TECHNOLOGIST(BigDecimal.valueOf(20000), BigDecimal.valueOf(30000), 1.0, 3),
    ENGINEER(BigDecimal.valueOf(30000), BigDecimal.valueOf(40000), 2.0, 6),
    LEAD_ENGINEER(BigDecimal.valueOf(40000), BigDecimal.valueOf(55000), 10.0, 8),
    CHIEF_ENGINEER(BigDecimal.valueOf(55000), BigDecimal.valueOf(65000), 15.0, 10),
    UNDEFINED(null, null, null, null);

    private final BigDecimal salaryMin;
    private final BigDecimal salaryMax;
    private final Double workExperience;
    private final Integer countTasks;

    public static Position definePosition(String position) { //todo название definePosition //  Done
        log.info("Was calling definePosition. Input position: {}", position);
        MessageService messageService = new MessageServiceImp();
        if (messageService.getMessage(INTERN).equals(position)) {
            return INTERN;
        } else if (messageService.getMessage(TECHNOLOGIST).equals(position)) {
            return TECHNOLOGIST;
        } else if (messageService.getMessage(ENGINEER).equals(position)) {
            return ENGINEER;
        } else if (messageService.getMessage(LEAD_ENGINEER).equals(position)) {
            return LEAD_ENGINEER;
        } else if (messageService.getMessage(CHIEF_ENGINEER).equals(position)) {
            return CHIEF_ENGINEER;
        } else {
            return UNDEFINED;
        }
    }

    public static boolean checkExperienceMatchingPosition(Position positionPerson, String experience) {
        log.info("Was calling checkExperienceMatchingPosition. Input positionPerson: {} experience: {}",
                positionPerson,
                experience);
        if (positionPerson.getWorkExperience().compareTo(Double.valueOf(experience)) > 0) {
            return false;
        }
        return true;
    }

    public static boolean checkSalaryMatchingPosition(Position positionPerson, BigDecimal salaryPerson) {
        log.info("Was calling checkSalaryMatchingPosition. Input positionPerson: {} salaryPerson: {}",
                positionPerson,
                salaryPerson);
        if (positionPerson.getSalaryMin().compareTo(salaryPerson) > 0
                || positionPerson.getSalaryMax().compareTo(salaryPerson) < 0) {
            return false;
        }
        return true;
    }

    public static boolean checkAvailableCountTasksToPerson(int countTasks, PersonEntity personEntity) {
        log.info("Was calling checkAvailableCountTasksToPerson. Input personEntity: {} countTasks: {}",
                personEntity,
                countTasks); //todo вот так опрятнее выглядит. не нравится использование "+" в логах (много места занимает и выглядит не оч). Везде где есть вставка значений в логи сделать такой вид. + желательно, делать лог в одну строку, но если не получается, то сделать, как здесь
                            //  done
        if (personEntity.getTasks().size() < personEntity.getPosition().getCountTasks()
                && personEntity.getCountAvailableTasks() >= countTasks) {
            return false;
        }
        return true;
    }

}
