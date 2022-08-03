package com.example.personmenegement.entity;

import com.example.personmenegement.types.Priority;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity// todo лучше эту аннотацию ставить ближе к классу, а то не видно сразу
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String uid;// todo пропиши аннотации @Column для лучшей читаемости
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private PersonEntity person;

    // todo используй lombok
    @Override
    public String toString() {
        return "TaskEntity[ id: " + id + ", uid: " + uid + ", description: " + description + ", priority: " + priority + " ]";
    }
}
