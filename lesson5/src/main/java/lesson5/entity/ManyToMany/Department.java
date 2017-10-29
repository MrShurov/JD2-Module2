package lesson5.entity.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "DEPARTMENT3")
public class Department {
    @Id
    @Column(name = "DEPARTMENT_ID")
    @GeneratedValue
    private Long id;
    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;
    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> people = new ArrayList<>();;

    public Department(String name){
        this.departmentName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        if (!super.equals(o)) return false;

        Department that = (Department) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getDepartmentName() != null ? !getDepartmentName().equals(that.getDepartmentName()) : that.getDepartmentName() != null)
            return false;
        return getPeople() != null ? getPeople().equals(that.getPeople()) : that.getPeople() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getDepartmentName() != null ? getDepartmentName().hashCode() : 0);
        result = 31 * result + (getPeople() != null ? getPeople().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", people=" + people +
                '}';
    }
}
