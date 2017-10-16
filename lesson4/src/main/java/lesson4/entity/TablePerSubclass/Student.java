package lesson4.entity.TablePerSubclass;

import lesson4.entity.TablePerSubclass.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STUDENT1")
@PrimaryKeyJoinColumn(name = "PERSON1_ID")
public class Student extends Person{
    private String grade;
}
