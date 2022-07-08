package com.example.personmenegement.entity;

import com.example.personmenegement.types.Position;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

// todo аккуратнее с этой аннотацией @Data. Она генерит equals && hashcode на все поля + у тебя уже есть аннотации для конструкторов, просто лучше добавь @Getter @Setter
//  done
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// todo лучше делать IDENTITY или SEQUENCE, почитай про отличия
    @Column(name = "id", nullable = false)            //   done
    private Long id;
    private String name;

    private Integer age;

    private String email;

    @Enumerated(EnumType.STRING)
    private Position position;

    private BigDecimal salary;

    private Double experience;

}
