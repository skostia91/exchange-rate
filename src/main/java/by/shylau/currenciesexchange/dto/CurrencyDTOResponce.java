package by.shylau.currenciesexchange.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrencyDTOResponce {
    private String code;

    private String name;

    private String sign;
}
