package com.app.services;

import javafx.collections.ObservableMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoadCsvTest {

    @Test
    void thereIs246Countries() {
        //when
        LoadCsv.INSTANCE.loadFromFile("src/main/resources/data.csv");
        //given
        ObservableMap<String,String> countries = LoadCsv.INSTANCE.getCountriesMap();
        //then
        assertEquals(246, countries.size());
    }

    @Test
    void thereIsPLforThePoland() {
        //given
        LoadCsv.INSTANCE.loadFromFile("src/main/resources/data.csv");
        //when
        ObservableMap<String,String> countries = LoadCsv.INSTANCE.getCountriesMap();
        //then
        assertEquals("PL",countries.get("Poland"));
    }
}