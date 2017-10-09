package lesson3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Access(AccessType.FIELD)
    @Column(updatable = false)
    private String username;
    @Access(AccessType.PROPERTY)
    @Column
    private String password;
    @Column(insertable = false)
    private Double balance;
}