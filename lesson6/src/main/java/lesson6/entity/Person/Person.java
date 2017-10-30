package lesson6.entity.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    @Column/*(name = "NAME")*/
    private String name;
    @Column/*(name = "SURNAME")*/
    private String surname;
    @OneToOne( cascade = CascadeType.PERSIST)
    private PersonInfo personInfo;
}
