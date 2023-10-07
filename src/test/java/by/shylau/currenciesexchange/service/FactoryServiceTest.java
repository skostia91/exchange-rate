package by.shylau.currenciesexchange.service;

import by.shylau.currenciesexchange.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FactoryServiceTest {
    private static final String test = "USDRUB";

    @Test
    public void testGetConvertStringTargetCode() {
        FactoryService factoryService = new FactoryService();

        // Проверяем, что метод возвращает правильный результат для строки "USDJPY"
        String result1 = factoryService.getConvertStringTargetCode("USDJPY");
        assertEquals("JPY", result1);

        // Проверяем, что метод возвращает правильный результат для строки "EURGBP"
        String result2 = factoryService.getConvertStringTargetCode("EURGBP");
        assertEquals("GBP", result2);
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
    public void testGetConvertStringBaseCode() {
        FactoryService factoryService = new FactoryService();

        // Проверяем, что при передаче строки длиной меньше 6 символов выбрасывается BadRequestException
        assertThrows(BadRequestException.class, () -> factoryService.getConvertStringBaseCode("USD"));

        // Проверяем, что при передаче строки длиной равной 6 символам возвращается первые 3 символа
        String result = factoryService.getConvertStringBaseCode("USDJPY");
        assertEquals("USD", result);

        // Проверяем, что при передаче строки длиной больше 6 символов возвращается первые 3 символа
        result = factoryService.getConvertStringBaseCode("EURGBPUSD");
        assertEquals("EUR", result);
    }

    @Test
    public void testGetDTO() {

    }
}