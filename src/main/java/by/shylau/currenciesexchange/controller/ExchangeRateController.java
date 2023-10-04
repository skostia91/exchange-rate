package by.shylau.currenciesexchange.controller;

import by.shylau.currenciesexchange.dto.ExchangeAmountDTO;
import by.shylau.currenciesexchange.dto.ExchangeRateDTOResponce;
import by.shylau.currenciesexchange.dto.ExchangeRateDTORequest;
import by.shylau.currenciesexchange.service.CurrenciesService;
import by.shylau.currenciesexchange.service.ExchangeRatesService;
import by.shylau.currenciesexchange.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExchangeRateController {
    private final ExchangeRatesService exchangeRatesService;
    private final FactoryService factoryService;
    private final CurrenciesService currenciesService;

    @Autowired
    public ExchangeRateController(ExchangeRatesService exchangeRatesService, FactoryService factoryService, CurrenciesService currenciesService) {
        this.exchangeRatesService = exchangeRatesService;
        this.factoryService = factoryService;
        this.currenciesService = currenciesService;
    }

    //работает, не трогай
    @GetMapping("/exchangeRates")
    public List<ExchangeRateDTORequest> getExchangeRateList() {
        return factoryService.getDTO(exchangeRatesService.getAllExchangeRates());
    }

    //работает, не трогай
    @GetMapping("/exchangeRate/{rate}")
    public ExchangeRateDTORequest getRate(@PathVariable("rate") String code) {

        return factoryService.converterExchangeRateIntoExchangeRateDTO(exchangeRatesService.getExchangeRate(
                        factoryService.convertBaseId(code), factoryService.convertTargetId(code)));
    }

    @PostMapping("/exchangeRates")
    public ExchangeRateDTORequest addExchangeRate(ExchangeRateDTOResponce exchangeRateDTOResponce) {//добавить валидацию
        exchangeRatesService.add(factoryService.convertExchangeDTOIntoExchange(exchangeRateDTOResponce));
        return getLastElementIntoDB();
    }

    @PatchMapping("/exchangeRate/{code}")
    public ExchangeRateDTORequest updateExchangeRate(@PathVariable("code") String code, double rate) {//добавить валидацию
        var exchangeRate = exchangeRatesService.getExchangeRate(
                factoryService.convertBaseId(code), factoryService.convertTargetId(code));
        exchangeRate.setRate(rate);
        exchangeRatesService.add(exchangeRate);
        return getLastElementIntoDB();
    }

    public ExchangeRateDTORequest getLastElementIntoDB() {
        int size = factoryService.getDTO(exchangeRatesService.getAllExchangeRates()).size();
        return factoryService.getDTO(exchangeRatesService.getAllExchangeRates()).get(size - 1);
    }

    @GetMapping("/exchange")
    public ExchangeAmountDTO getLastElementIntoDB(@RequestParam("from") String from,
                                                       @RequestParam("to") String to,
                                                       @RequestParam("amount") double amount) {

        double rate = exchangeRatesService.getExchangeRate(currenciesService.findByCode(from).getId(),
                currenciesService.findByCode(to).getId()).getRate();

        System.err.println(rate * amount);

        return new ExchangeAmountDTO(
                currenciesService.findByCode(from),
                currenciesService.findByCode(to),
                rate,
                amount,
                rate * amount
        );
    }
}