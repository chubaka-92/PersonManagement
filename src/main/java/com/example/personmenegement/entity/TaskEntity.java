package com.example.personmenegement.entity;

import com.example.personmenegement.types.Priority;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String uid;
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private PersonEntity person;
}
