package lesson6.entity.Person;

import lesson6.entity.Person.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class PersonInfo {
    @Id
    @GeneratedValue
    @Column/*(name = "PERSON_INFO_ID")*/
    private Long id;
    @Column/*(name = "CITY")*/
    private String city;
    @Column/*(name = "STREET")*/
    private String street;
    @Column/*(name = "PHONE_NUMBER")*/
    private String phoneNumber;
    @Column
    private int amountCars;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Person person;
}
