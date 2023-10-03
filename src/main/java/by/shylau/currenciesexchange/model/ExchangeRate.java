package by.shylau.currenciesexchange.model;

import by.shylau.currenciesexchange.service.CurrenciesService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Currency;

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

    private Double rate;


}