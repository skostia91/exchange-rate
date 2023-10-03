package by.shylau.currenciesexchange.controller;

import by.shylau.currenciesexchange.dto.CurrencyDTOResponce;
import by.shylau.currenciesexchange.model.Currencie;
import by.shylau.currenciesexchange.service.CurrenciesService;
import by.shylau.currenciesexchange.service.FactoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/currencies")
public class CurrenciesController {
    private CurrenciesService currenciesService;
    private FactoryService factoryService;

    @Autowired
    public CurrenciesController(CurrenciesService currenciesService, FactoryService factoryService) {
        this.currenciesService = currenciesService;
        this.factoryService = factoryService;
    }

    @GetMapping()
    public List<Currencie> getCurrencies() {
        return currenciesService.findAll();
    }

    @PostMapping()//работает, не трогай
    public Currencie addCurrencies(CurrencyDTOResponce currencieDTO) {//добавить @Valid
        currenciesService.addCurrencies(factoryService.convertCurrencyDTOIntoCurrency(currencieDTO));

        return currenciesService.findById(currenciesService.findAll().size());
    }

    @GetMapping("/{id}")
    public Currencie getCurrencyById(@PathVariable("id") int id) {
        return currenciesService.findById(id);
    }

    @GetMapping("/currency/{currency}")
    public Currencie getCurrencyByCode(@PathVariable("currency") String code) {
        return currenciesService.findByCode(code);
    }
}
