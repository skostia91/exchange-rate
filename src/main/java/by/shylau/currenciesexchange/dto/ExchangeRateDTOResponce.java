package by.shylau.currenciesexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExchangeRateDTOResponce {
    private String baseCurrencyId;//уникальный

    private String targetCurrencyId;//уникальный

    private Double rate;
}
