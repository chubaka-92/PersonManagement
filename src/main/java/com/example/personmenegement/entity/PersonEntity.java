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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class PersonEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

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

    @OneToMany()
    @JoinTable(
            name = "task",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<TaskEntity> tasks = new ArrayList<>();

}
