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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class PersonEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @Enumerated(EnumType.STRING)
    private Position position;
    private BigDecimal salary;
    private Double experience;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<TaskEntity> tasks;

    /**
     * @return количество задач, которые может взять персона в работу
     * */
    public Integer getCountAvailableTasks(){
        return position.getCountTasks() - tasks.size();
    }

}
