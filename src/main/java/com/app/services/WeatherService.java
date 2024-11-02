package com.app.services;

import com.app.model.WeatherInfo;
import com.app.webclient.weather.WeatherClient;
import com.app.webclient.weather.dto.OpenWeatherDto;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class WeatherService extends Service {

    private final WeatherClient weatherClient = new WeatherClient();

    public WeatherInfo getTodayWeather(String city,String country){
        OpenWeatherDto openWeatherDto = weatherClient.getWeatherForCity(city,country);
        return new WeatherInfo.WeatherInfoBuilder()
                .temperature(openWeatherDto.getMain().getTemp())
                .description(openWeatherDto.getWeather().get(0).getDescription())
                .build();

    }

    @Override
    protected Task createTask() {
        return null;
    }
}
