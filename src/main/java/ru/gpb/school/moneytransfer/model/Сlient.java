package ru.gpb.school.moneytransfer.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Сlient {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String Id;
    private String name;
    private String surname;
    private String address;
    private String countryOfPassport;
    private String serialOfPassport;
    private String numberOfPassport;
    private String numberOfTelephone;

}
