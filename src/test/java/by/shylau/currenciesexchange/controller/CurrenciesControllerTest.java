package by.shylau.currenciesexchange.controller;

import by.shylau.currenciesexchange.model.Currencie;
import by.shylau.currenciesexchange.repository.CurrenciesRepository;
import by.shylau.currenciesexchange.service.CurrenciesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CurrenciesControllerTest {

    @InjectMocks
    CurrenciesController controller;

    @Mock
    CurrenciesService currenciesService;

    @Test
    void getCurrencie_ReturnResponceEntity() {
        var task = List.of(
                new Currencie(1, "test", "test", "test"),
                new Currencie(2, "test", "test", "test")
        );

        when(currenciesService.findAll()).thenReturn(task);
        //Mockito.doReturn(task).when(this.repository).findAll();

        var entity = controller.getCurrencies().getStatusCode();

        assertEquals(2, currenciesService.findAll().size());

        assertEquals(HttpStatus.OK, entity.is2xxSuccessful());

    }


}