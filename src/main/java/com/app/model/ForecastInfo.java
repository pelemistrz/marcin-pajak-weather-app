package com.app.model;

import java.util.ArrayList;
import java.util.List;

public class ForecastInfo {
    private List<WeatherInfo> forecastList = new ArrayList<>();

    public List<WeatherInfo> getForecastList() {
        return forecastList;
    }
    public void addWeatherInfo(WeatherInfo weatherInfo) {
        forecastList.add(weatherInfo);
    }

}
