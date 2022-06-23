package com.example.personmenegement.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id; // todo обычно используется Long-обертка. Таким образом можно понимать задано ли значение id или нет
                    // done
    private String name;

    private Integer age; // todo обертка
                        //   done

    private String email;

    private String position; // todo можешь сделать поле enum и повесить аннотацию @Enumerated

    private BigDecimal salary; // todo обертка
                            //     done

    // todo используй lombok
    //  done

}
