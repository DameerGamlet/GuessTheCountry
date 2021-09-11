package com.example.guessthecountry;

public class GlobalVariable {
    final String COUNTRY_SAVED = "country_saved",
            COUNTRY_ARRAY_SAVED = "array_saved",
            SAVE_ONE = "result_one",
            SAVE_TWO = "result_two",
            ASIA_SAVED = "asia_saved",
            COUNTRY_GERB_SAVED = "asian_gerb_saved",
            COUNTRY_EURO_SAVED = "euro_cap",
            COUNTRY_EURO_ARRAY_SAVED = "euro_array",
            SOUND = "sound_of_or_off";
    public int countFlags = 194,
            asian_flag = new CountryArrays().asian_countries_images.length,
            asian_gerb = new CountryArrays().asian_gerb_images.length,
            countEuroFlags = new CountryArrays().euroCountry.length;
}
