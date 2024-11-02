package com.app.model;

public class WeatherDto {
    private String description;
    private float temperature;

    public String getDescription() {
        return description;
    }

    public float getTemperature() {
        return temperature;
    }

    private WeatherDto(WeatherDtoBuilder builder) {
        this.description = builder.description;
        this.temperature = builder.temperature;
    }

    public static class WeatherDtoBuilder {
        private String description;
        private float temperature;
        public WeatherDtoBuilder description(String description) {
            this.description = description;
            return this;
        }
        public WeatherDtoBuilder temperature(float temperature) {
            this.temperature = temperature;
            return this;
        }
        public WeatherDto build() {
            return new WeatherDto(this);
        }
    }
}
