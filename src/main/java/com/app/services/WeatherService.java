package com.app.services;

import com.app.model.ForecastInfo;
import com.app.model.WeatherInfo;
import com.app.webclient.weather.WeatherClient;
import com.app.webclient.weather.dto.OpenWeatherDto;
import com.app.webclient.weather.dto.OpenWeatherForecastDto;
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

    public ForecastInfo getForecast(String city,String country){
        OpenWeatherForecastDto openWeatherForecastDto = weatherClient.getForecastForCity(city,country);
        ForecastInfo forecastInfo = new ForecastInfo();

        for(int i = 0;i<25;i=i+8){
            WeatherInfo weatherInfo = new WeatherInfo.WeatherInfoBuilder()
                    .temperature(openWeatherForecastDto.getList().get(i).getMain().getTemp())
                    .description(openWeatherForecastDto.getList().get(i).getWeather().get(0).getDescription())
                    .build();
            forecastInfo.addWeatherInfo(weatherInfo);
        }
        return forecastInfo;
    }




    @Override
    protected Task createTask() {
        return null;
    }
}
