package by.shylau.currenciesexchange.controller;

import by.shylau.currenciesexchange.model.Currencie;
import by.shylau.currenciesexchange.service.CurrenciesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/currencies")
public class CurrenciesController {
    private CurrenciesService currenciesService;

    @Autowired
    public CurrenciesController(CurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
    }

    @GetMapping()
    public List<Currencie> getCurrencies() {
        return currenciesService.findAll();
    }

    @PostMapping()
    public void addCurrencies(@RequestBody Currencie currencie) {//добавить @Valid
            currenciesService.addCurrencies(currencie);
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
