package com.app.model;

public class WeatherInfo {
    private String description;
    private float temperature;

    public String getDescription() {
        return description;
    }

    public float getTemperature() {
        return temperature;
    }

    private WeatherInfo(WeatherInfoBuilder builder) {
        this.description = builder.description;
        this.temperature = builder.temperature;
    }

    public static class WeatherInfoBuilder {
        private String description;
        private float temperature;
        public WeatherInfoBuilder description(String description) {
            this.description = description;
            return this;
        }
        public WeatherInfoBuilder temperature(float temperature) {
            this.temperature = temperature;
            return this;
        }
        public WeatherInfo build() {
            return new WeatherInfo(this);
        }
    }
}
