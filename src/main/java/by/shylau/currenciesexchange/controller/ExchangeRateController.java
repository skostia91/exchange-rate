package by.shylau.currenciesexchange.controller;

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

    @GetMapping("/exchangeRates")
    public List<ExchangeRateDTORequest> getExchangeRateList() {
        return factoryService.getDTO(exchangeRatesService.getAllExchangeRates());
    }

    @GetMapping("/exchangeRate/{rate}")
    public ExchangeRateDTORequest getRate(@PathVariable("rate") String code) {

        return factoryService.converterExchangeRateIntoExchangeRateDTO(exchangeRatesService.getExchangeRate(
                        factoryService.convertBaseId(code), factoryService.convertTargetId(code)));
    }

    @PostMapping("/exchangeRates")
    public ExchangeRateDTORequest addExchangeRateList(@RequestBody ExchangeRateDTOResponce exchangeRateDTOResponce) {//добавить валидацию
        System.err.println(exchangeRateDTOResponce);

//        currenciesService.addEchangeRate(currencie);

        return null;


    }

}
