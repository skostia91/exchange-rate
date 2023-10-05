package by.shylau.currenciesexchange.controller;

import by.shylau.currenciesexchange.dto.ExchangeAmountDTO;
import by.shylau.currenciesexchange.dto.ExchangeRateDTOResponce;
import by.shylau.currenciesexchange.dto.ExchangeRateDTORequest;
import by.shylau.currenciesexchange.exception.BadRequestException;
import by.shylau.currenciesexchange.exception.NotFoundException;
import by.shylau.currenciesexchange.service.CurrenciesService;
import by.shylau.currenciesexchange.service.ExchangeRatesService;
import by.shylau.currenciesexchange.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/exchangeRates")
    public ResponseEntity<List<ExchangeRateDTORequest>> getExchangeRateList() {
        return new ResponseEntity<>(factoryService.getDTO(exchangeRatesService.getAllExchangeRates()), HttpStatus.OK);
    }

    @GetMapping("/exchangeRate/{rate}")
    public ResponseEntity<ExchangeRateDTORequest> getRate(@PathVariable("rate") String code) {
        return new ResponseEntity<>(factoryService.converterExchangeRateIntoExchangeRateDTO(exchangeRatesService.getExchangeRate(
                        factoryService.convertBaseId(code), factoryService.convertTargetId(code))), HttpStatus.OK);
    }

    @PostMapping("/exchangeRates")
    public ResponseEntity<ExchangeRateDTORequest> addExchangeRate(ExchangeRateDTOResponce exchangeRateDTOResponce) {//добавить валидацию
        if(exchangeRateDTOResponce.getBaseCurrencyId() == null ||
                exchangeRateDTOResponce.getTargetCurrencyId() == null ||
                exchangeRateDTOResponce.getRate() == 0) {
            throw new BadRequestException("не корректно заполнены поля");
        }

        exchangeRatesService.add(factoryService.convertExchangeDTOIntoExchange(exchangeRateDTOResponce));

        return new ResponseEntity<>(getLastElementIntoDB(), HttpStatus.OK);
    }

    @PatchMapping("/exchangeRate/{code}")
    public ResponseEntity<ExchangeRateDTORequest> updateExchangeRate(@PathVariable("code") String code, double rate) {
        if (code == null || rate == 0) {
            throw new BadRequestException("не корректно введены данные");
        }

        var exchangeRate = exchangeRatesService.getExchangeRate(
                factoryService.convertBaseId(code), factoryService.convertTargetId(code));
        if (exchangeRate == null) {
            throw new NotFoundException("нет такой валюты");
        }

        exchangeRate.setRate(rate);
        exchangeRatesService.add(exchangeRate);
        return new ResponseEntity<>(getLastElementIntoDB(), HttpStatus.OK);
    }

    @GetMapping("/exchange")
    public ExchangeAmountDTO getLastElementIntoDB(@RequestParam("from") String from,
                                                       @RequestParam("to") String to,
                                                       @RequestParam("amount") double amount) {

        if (from == null || to == null || amount == 0) {
            throw new BadRequestException("не корректно введены данные");
        }

        double rate = exchangeRatesService.getExchangeRate(currenciesService.findByCode(from).getId(),
                currenciesService.findByCode(to).getId()).getRate();

        return new ExchangeAmountDTO(
                currenciesService.findByCode(from),
                currenciesService.findByCode(to),
                rate,
                amount,
                rate * amount);
    }

    public ExchangeRateDTORequest getLastElementIntoDB() {
        int size = factoryService.getDTO(exchangeRatesService.getAllExchangeRates()).size();
        return factoryService.getDTO(exchangeRatesService.getAllExchangeRates()).get(size - 1);
    }
}