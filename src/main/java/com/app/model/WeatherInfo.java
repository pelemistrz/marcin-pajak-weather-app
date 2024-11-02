package com.app.model;

public class WeatherInfo {
    private String description;
    private double temperature;

    public String getDescription() {
        return description;
    }

    public double getTemperature() {
        return temperature;
    }

    private WeatherInfo(WeatherInfoBuilder builder) {
        this.description = builder.description;
        this.temperature = builder.temperature;
    }

    public static class WeatherInfoBuilder {
        private String description;
        private double temperature;

        public WeatherInfoBuilder description(String description) {
            this.description = description;
            return this;
        }
        public WeatherInfoBuilder temperature(double temperature) {
            this.temperature = temperature;
            return this;
        }
        public WeatherInfo build() {
            return new WeatherInfo(this);
        }
    }
}
