package com.example.personmenegement.entity;

import com.example.personmenegement.types.Position;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString(exclude = "tasks")
// todo при вызове toString у тебя полетят запросы в базу для подтягивания тасков, так как они по умолчанию Lazy. Сделай exclude
//  DONE
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class PersonEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;// todo пропиши аннотации @Column для лучшей читаемости  //  DONE

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "experience")
    private Double experience;

    @OneToMany(mappedBy = "person")
    // todo cascade лучше не использовать, так как это может привести к нежелательным удалениям. Лучше процедуру удаления делать в несколько этапов
    //  DONE
    private List<TaskEntity> tasks = new ArrayList<>();//todo лучше инициализировать : private List<TaskEntity> tasks = new ArrayList<>(); чтобы не словить NPE  //  DONE

    // todo какая-то логика в классе Entity это нехорошо. Вынести в класс валидации + у тебя будет тут могут дополнительно тянуться таски отдельным запросом
    //  Done. перенес метод в те классы где он используется

}
