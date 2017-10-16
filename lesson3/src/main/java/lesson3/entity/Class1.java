package lesson3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Class1 {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer Id;
        private String username;
    }
