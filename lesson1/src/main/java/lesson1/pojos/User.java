package lesson1.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String role;
    @Column
    private String status;
    @Column
    private Double balance;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}