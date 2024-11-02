package com.app.webclient.weather.dto;

public class OpenWeatherDto {
    private OpenWeatherMainDto openWeatherMainDto;
    private OpenWeatherWeatherDto openWeatherWeatherDto;

    public OpenWeatherMainDto getOpenWeatherMainDto() {
        return openWeatherMainDto;
    }

    public OpenWeatherWeatherDto getOpenWeatherWeatherDto() {
        return openWeatherWeatherDto;
    }


}
