package com.app.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum LoadCsv {
        INSTANCE;


    private ObservableMap<String,String> countriesMap = FXCollections.observableHashMap();

    public void loadFromFile(String fileName) {
        Map<String,String> countries = new HashMap<>();
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine())!=null){
                String[] fields = line.split(",");
                countries.put(fields[0],fields[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       countriesMap.putAll(countries);
    }

    public ObservableMap<String,String> getCountriesMap() {

        return countriesMap;
    }


}