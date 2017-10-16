package lesson3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Class3 {
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        private Integer id;
        @Column
        private String username;
}
