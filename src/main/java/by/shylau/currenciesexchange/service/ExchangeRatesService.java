package by.shylau.currenciesexchange.service;

import by.shylau.currenciesexchange.model.ExchangeRate;
import by.shylau.currenciesexchange.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRatesService {
    private final ExchangeRateRepository exchangeRatesRepository;

    @Autowired
    public ExchangeRatesService(ExchangeRateRepository exchangeRatesRepository) {
        this.exchangeRatesRepository = exchangeRatesRepository;
    }

    public List<ExchangeRate> getAllExchangeRates() {
        return exchangeRatesRepository.findAll();
    }

    public ExchangeRate getExchangeRate(int base, int target) {
        return exchangeRatesRepository.findByBaseCurrencyIdAndAndTargetCurrencyId(base, target);
    }
}
