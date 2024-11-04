package com.app;

import com.app.webclient.weather.WeatherClient;
import com.app.webclient.weather.dto.OpenWeatherDto;
import com.app.webclient.weather.dto.OpenWeatherForecastDto;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        WeatherClient weatherClient = new WeatherClient();
        System.out.println("hi");
//     OpenWeatherForecastDto op= weatherClient.getForecastForCity("cairo","eg");
    }

}
