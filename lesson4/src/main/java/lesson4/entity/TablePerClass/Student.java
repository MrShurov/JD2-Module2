package lesson4.entity.TablePerClass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STUDENT2")
@PrimaryKeyJoinColumn(name = "PERSON2_ID")
public class Student extends Person {
    private String grade;

    public Student(String s1,String s2,String s3){
        super(null,s1,s2);
        this.grade = s3;
    }
}
