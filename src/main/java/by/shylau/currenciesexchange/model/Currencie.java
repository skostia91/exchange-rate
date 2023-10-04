package by.shylau.currenciesexchange.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static by.shylau.currenciesexchange.util.Constants.*;

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

    @NotBlank(message = FIELD_NOT_BLANK_MESSAGE)
    @Size(min = CODE_SIZE_MIN, max = CODE_SIZE_MAX, message = CODE_SIZE_MESSAGE)
    private String code;

    @NotBlank(message = FIELD_NOT_BLANK_MESSAGE)
    @Size(min = NAME_SIZE_MIN, max = NAME_SIZE_MAX, message = NAME_SIZE_MESSAGE)
    private String name;

    @NotBlank(message = FIELD_NOT_BLANK_MESSAGE)
    @Size(min = SIGN_SIZE_MIN, max = SIGN_SIZE_MAX, message = SIGN_SIZE_MESSAGE)
    private String sign;
}


