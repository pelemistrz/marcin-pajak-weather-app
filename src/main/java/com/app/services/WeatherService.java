package com.app.services;

import com.app.errors.CityNotFound;
import com.app.model.ForecastInfo;
import com.app.model.WeatherInfo;
import com.app.webclient.weather.WeatherClient;
import com.app.webclient.weather.dto.OpenWeatherDto;
import com.app.webclient.weather.dto.OpenWeatherForecastDto;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class WeatherService extends Service {

    private final WeatherClient weatherClient ;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public WeatherInfo getTodayWeather(String city, String country) throws CityNotFound {

        OpenWeatherDto openWeatherDto = weatherClient.getWeatherForCity(city,country);

        if(openWeatherDto == null){
            throw new CityNotFound("City not found");
        }
        return new WeatherInfo.WeatherInfoBuilder()
                .temperature(openWeatherDto.getMain().getTemp())
                .description(openWeatherDto.getWeather().get(0).getDescription())
                .build();
    }

    public ForecastInfo getForecast(String city,String country) throws CityNotFound {
        OpenWeatherForecastDto openWeatherForecastDto = weatherClient.getForecastForCity(city,country);

        if(openWeatherForecastDto==null){
            throw new CityNotFound("City not found");
        }

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
