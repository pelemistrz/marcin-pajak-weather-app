package com.app.webclient.weather.dto;

public class OpenWeatherMainDto {
    private double temp;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "OpenWeatherMainDto{" +
                "temp=" + temp +
                '}';
    }
}
