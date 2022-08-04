package com.example.personmenegement.entity;

import com.example.personmenegement.types.Priority;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity// todo лучше эту аннотацию ставить ближе к классу, а то не видно сразу //  DONE
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "uid")
    private String uid;// todo пропиши аннотации @Column для лучшей читаемости // DONE

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private PersonEntity person;

    // todo используй lombok
    //  Done
}


