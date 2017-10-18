package lesson5.entity.OneToMany;

import lesson5.entity.OneToMany.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Department {
    @Id
    @Column(name = "DEPARTMENT_ID")
    @GeneratedValue
    private Long id;
    @Column
    private String departmentName;
    @OneToMany
    private Set<Person> people = new HashSet<>();

    public Department(String name){
        this.departmentName = name;
    }
}
