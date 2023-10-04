package by.shylau.currenciesexchange.util;

public final class Constants {
    //Validation Value
    public static final String VALUE_MIN_MESSAGE = "минимум 0";
    public static final String VALUE_MAX_MESSAGE = "максимум 2 000 000 000";
    public static final int VALUE_MAX = 2_000_000_000;
    public static final int VALUE_MIN = 0;


    //Validation Value

    public static final int CODE_SIZE_MIN = 2;
    public static final int CODE_SIZE_MAX = 3;

    public static final String CODE_SIZE_MESSAGE = "Код должен быть от 2 до 3 символов длинной";

    public static final int NAME_SIZE_MIN = 3;
    public static final int NAME_SIZE_MAX = 30;
    public static final String NAME_SIZE_MESSAGE = "Имя должен быть от 3 до 30 символов длинной";

    public static final String FIELD_NOT_BLANK_MESSAGE = "Поле не должно быть пустым";

    public static final int SIGN_SIZE_MIN = 1;
    public static final int SIGN_SIZE_MAX = 3;
    public static final String SIGN_SIZE_MESSAGE = "Символ валюты должен быть от 1 до 3 символов длинной";

    public static final int RATE_MIN = 0;
    public static final int RATE_MAX = 2_000_000_000;
    public static final String RATE_MIN_MESSAGE = "Курс должен быть минимум 0";
    public static final String RATE_MAX_MESSAGE = "Символ валюты должен быть от 1 до 3 символов длинной";


}
