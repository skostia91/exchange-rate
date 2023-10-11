package by.shylau.currenciesexchange.service;

import by.shylau.currenciesexchange.exception.BadRequestException;
import by.shylau.currenciesexchange.model.Currencie;
import by.shylau.currenciesexchange.repository.CurrenciesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FactoryServiceTest {
    private static final String test = "USDRUB";

    @InjectMocks
    private CurrenciesService service;

    @Mock
    private CurrenciesRepository repository;


    @Test
    public void testGetConvertStringTargetCode() {
        Currencie currencie1 = new Currencie();
        Currencie currencie2 = new Currencie();
        Mockito.when(service.findAll()).thenReturn(List.of(currencie1, currencie2));
        assertNotNull(service.findAll());


        assertEquals(2, service.findAll().size());
    }


    @Test
    void getConvertStringBaseCode() {
        String a = "";
        assertAll(
                () -> assertEquals(6, test.length()),
                () -> assertNotEquals(test, a)
        );
    }


    @Test
    public void testGetDTO() {

    }
}