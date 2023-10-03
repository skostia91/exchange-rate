package by.shylau.currenciesexchange.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExchangeRateDTOResponce {
    private String baseCurrencyId;//уникальный

    private String targetCurrencyId;//уникальный

    private Double rate;
}
