package by.shylau.currenciesexchange.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExchangeRateDTOResponce {
    private String baseCurrencyId;

    private String targetCurrencyId;

    private Double rate;
}
