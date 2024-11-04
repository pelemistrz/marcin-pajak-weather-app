package com.app.webclient.weather.dto;

import java.util.List;

public class OpenWeatherForecastDto {
    private List<OpenWeatherDto> list;



    public List<OpenWeatherDto> getList() {
        return list;
    }

    public void setList(List<OpenWeatherDto> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "OpenWeatherForecastDto{" +
                "list=" + list +
                '}';
    }
}
