package com.example.personmenegement.entity;

import com.example.personmenegement.types.Position;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
// todo при вызове toString у тебя полетят запросы в базу для подтягивания тасков, так как они по умолчанию Lazy. Сделай exlude
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class PersonEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;// todo пропиши аннотации @Column для лучшей читаемости
    private Integer age;
    private String email;

    @Enumerated(EnumType.STRING)
    private Position position;
    private BigDecimal salary;
    private Double experience;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
// todo cascade лучше не использовать, так как это может привести к нежелательным удалениям. Лучше процедуру удаления делать в несколько этапов
    private List<TaskEntity> tasks;//todo лучше инициализировать : private List<TaskEntity> tasks = new ArrayList<>(); чтобы не словить NPE

    /**
     * @return количество задач, которые может взять персона в работу
     */
    // todo какая-то логика в классе Entity это нехорошо. Вынести в класс валидации + у тебя будет тут могут дополнительно тянуться таски отдельным запросом
    public Integer getCountAvailableTasks() {
        return position.getCountTasks() - tasks.size();
    }

}
