package com.app.webclient.weather.dto;

import java.util.List;

public class OpenWeatherDto {
    private Integer dt;
    private OpenWeatherMainDto main;
    private List<OpenWeatherWeatherDto> weather;

    public OpenWeatherMainDto getMain() {
        return main;
    }
    public List<OpenWeatherWeatherDto> getWeather() {
        return weather;
    }

    public void setMain(OpenWeatherMainDto main) {
        this.main = main;
    }

    public void setWeather(List<OpenWeatherWeatherDto> weather) {
        this.weather = weather;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "OpenWeatherDto{" +
                "dt=" + dt +
                ", main=" + main +
                ", weather=" + weather +
                '}';
    }
}
