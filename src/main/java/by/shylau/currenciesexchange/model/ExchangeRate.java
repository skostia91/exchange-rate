package by.shylau.currenciesexchange.model;

import by.shylau.currenciesexchange.service.CurrenciesService;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Currency;

import static by.shylau.currenciesexchange.util.Constants.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "exchange_rates")
@Entity
@ToString
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //валюта которую меняют. foren key
    @Column(name = "base_currency_id")
    private int baseCurrencyId;//уникальный

    //валюта на которую поменяют. foren key
    @Column(name = "target_currency_id")
    private int targetCurrencyId;//уникальный

    @Min(value = RATE_MIN, message = RATE_MIN_MESSAGE)
    @Min(value = RATE_MAX, message = RATE_MAX_MESSAGE)
    private Double rate;


}