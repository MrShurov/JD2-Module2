package lesson4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("P")
public class Person {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
}
