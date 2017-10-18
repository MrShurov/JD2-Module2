package lesson5.entity.OneToOne;

import lesson5.entity.OneToOne.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PERSONINFO1")
public class PersonInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String phoneNumber;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Person person;
}
