package by.shylau.currenciesexchange.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "currencies")
@ToString
public class Currencie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    private String name;

    private String sign;
}


