package lesson4.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@MappedSuperclass
public class DataEntity {
    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    private LocalDateTime localDateTime;
}
