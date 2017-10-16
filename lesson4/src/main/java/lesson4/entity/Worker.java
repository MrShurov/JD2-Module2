package lesson4.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("W")
public class Worker extends Person{
    private String company;

    public
    Worker(String company){
        super();
        this.company = company;
    }
}
