package by.shylau.currenciesexchange.dto;

import by.shylau.currenciesexchange.model.Currencie;
import lombok.*;

@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateDTORequest {
    private int id;

    private Currencie baseCurrencyId;//уникальный

    private Currencie targetCurrencyId;//уникальный

    private Double rate;
}