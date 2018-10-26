package usitcc.amarillo.theteam889.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PET")
@Data
public class Pet {

    @Id
    @GeneratedValue
    private Long id;
    private String species;
    private int age;
    private String fedday;
    @Column( precision=7, scale=2)
    private BigDecimal price;
    private int discount;
    private String status;
}
