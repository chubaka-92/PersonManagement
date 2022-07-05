package com.example.personmenegement.entity;

import com.example.personmenegement.types.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data// todo аккуратнее с этой аннотацией. Она генерит equals && hashcode на все поля + у тебя уже есть аннотации для конструкторов, просто лучше добавь @Getter @Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// todo лучше делать IDENTITY или SEQUENCE, почитай про отличия
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    private Integer age;

    private String email;

    @Enumerated(EnumType.STRING)
    private Position position;

    private BigDecimal salary;

    private Double experience;

}
