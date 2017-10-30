package lesson6.entity.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @Column/*(name = "DEPARTMENT_ID")*/
    @GeneratedValue
    private Long id;
    @Column/*(name = "DEPARTMENT_NAME")*/
    private String departmentName;
}
