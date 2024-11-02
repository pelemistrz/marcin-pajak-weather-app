package com.app.webclient.weather.dto;
public class OpenWeatherWeatherDto {
    private String description;



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OpenWeatherWeatherDto{" +
                "description='" + description + '\'' +
                '}';
    }
}
