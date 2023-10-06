package by.shylau.currenciesexchange.service;

import by.shylau.currenciesexchange.dto.CurrencyDTOResponce;
import by.shylau.currenciesexchange.dto.ExchangeRateDTORequest;
import by.shylau.currenciesexchange.dto.ExchangeRateDTOResponce;
import by.shylau.currenciesexchange.exception.BadRequestException;
import by.shylau.currenciesexchange.model.Currencie;
import by.shylau.currenciesexchange.model.ExchangeRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactoryService {
    private final CurrenciesService currenciesService;

    @Autowired
    public FactoryService(CurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
    }

    public List<ExchangeRateDTORequest> getDTO(List<ExchangeRate> exchangeRateList) {
        List<ExchangeRateDTORequest> exchangeRateDTORequest = new ArrayList<>();

        for (int i = 0; i < exchangeRateList.size(); i++) {
            exchangeRateDTORequest.add(converterExchangeRateIntoExchangeRateDTO(exchangeRateList.get(i)));
        }
        return exchangeRateDTORequest;
    }

    public String getConvertStringBaseCode(String line) {
        if (line.length() < 6) {
            throw new BadRequestException("не указаны валюты");
        }
        return line.substring(0, 3);
    }

    public String getConvertStringTargetCode(String line) {
        return line.substring(3, 6);
    }

    public ExchangeRateDTORequest converterExchangeRateIntoExchangeRateDTO(ExchangeRate exchangeRate) {
        return new ExchangeRateDTORequest(
                exchangeRate.getId(),
                currenciesService.findById(exchangeRate.getBaseCurrencyId()),
                currenciesService.findById(exchangeRate.getTargetCurrencyId()),
                exchangeRate.getRate()
        );
    }

    public int convertBaseId(String code) {
        String baseCurrency = getConvertStringBaseCode(code);

        return currenciesService.findByCode(baseCurrency).getId();
    }

    public int convertTargetId(String code) {
        String targetCurrency = getConvertStringTargetCode(code);

        return currenciesService.findByCode(targetCurrency).getId();
    }

    public Currencie convertCurrencyDTOIntoCurrency(CurrencyDTOResponce currencyDTO) {
        var currencie = new Currencie();
        currencie.setCode(currencyDTO.getCode());
        currencie.setName(currencyDTO.getName());
        currencie.setSign(currencyDTO.getSign());

        return currencie;
    }

    public ExchangeRate convertExchangeDTOIntoExchange(ExchangeRateDTOResponce exchangeRateDTOResponce) {

    int base = currenciesService.findByCode(exchangeRateDTOResponce.getBaseCurrencyId()).getId();
    int target = currenciesService.findByCode(exchangeRateDTOResponce.getTargetCurrencyId()).getId();

    double rate = exchangeRateDTOResponce.getRate();
    var exchangeRate = new ExchangeRate();
        exchangeRate.setBaseCurrencyId(base);
        exchangeRate.setTargetCurrencyId(target);
        exchangeRate.setRate(rate);
    return exchangeRate;
    }

    public String converter(double money) {
        double a = money * 100;
        int b = (int) a;

        return b/100 + "." + b%100/10 + b%100%10;
    }
}
