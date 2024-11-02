package com.app.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoadCsv {
    private Map<String,String> countries = new HashMap<String,String>();

    public void loadFromFile(String fileName) {
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine())!=null){
                String[] tokens = line.split(",");
                countries.put(tokens[0],tokens[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getCountries() {
        return countries;
    }
}