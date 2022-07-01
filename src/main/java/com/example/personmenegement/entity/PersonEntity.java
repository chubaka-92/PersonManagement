package com.example.personmenegement.entity;

import com.example.personmenegement.types.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id; // todo обычно используется Long-обертка. Таким образом можно понимать задано ли значение id или нет
                    //   done
    private String name;

    private Integer age; // todo обертка
                        //   done

    private String email;

    @Enumerated(EnumType.STRING)
    private Position position; // todo можешь сделать поле enum и повесить аннотацию @Enumerated
    //                             done

    private BigDecimal salary; // todo обертка
                            //     done

    // todo используй lombok
    //  done

    private Double experience;

}
