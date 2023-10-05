package by.shylau.currenciesexchange.controller;

import by.shylau.currenciesexchange.dto.CurrencyDTOResponce;
import by.shylau.currenciesexchange.exception.BadRequestException;
import by.shylau.currenciesexchange.exception.InternalServerException;
import by.shylau.currenciesexchange.exception.NotFoundException;
import by.shylau.currenciesexchange.model.Currencie;
import by.shylau.currenciesexchange.service.CurrenciesService;
import by.shylau.currenciesexchange.service.FactoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Currencie>> getCurrencies() {
        List<Currencie> currencies = currenciesService.findAll();
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Currencie> addCurrencies(CurrencyDTOResponce currencieDTO) {
        currenciesService.addCurrencies(factoryService.convertCurrencyDTOIntoCurrency(currencieDTO));


        return new ResponseEntity<>(currenciesService.findById(currenciesService.findAll().size()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Currencie> getCurrencyById(@PathVariable("id") int id) {
        Currencie currencie = currenciesService.findById(id);
        if(currencie == null) {
            throw new NotFoundException("нет в БД с id = " + id);
        }
        return new ResponseEntity<>(currenciesService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/currency/{currency}")
    public ResponseEntity<Currencie> getCurrencyByCode(@PathVariable("currency") String code) {
        if (code == null) {
            throw new BadRequestException("не корректно введена валюта");
        }
        Currencie currencie = currenciesService.findById(id).orElseThrow(
                () -> new NotFoundException("нет в БД с id = " + id)));
        return new ResponseEntity<>(currenciesService.findByCode(code), HttpStatus.OK);
    }
}
