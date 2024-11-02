package com.app.webclient.weather;

import com.app.model.WeatherInfo;
import com.app.webclient.weather.dto.OpenWeatherDto;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class WeatherClient {
    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5";
    public static final String API_KEY = "4af1c105dcf10a54eab54afc6edd20eb";
    HttpClient client = HttpClient.newHttpClient();

    public WeatherInfo getWeatherForCity(String country, String city){
       OpenWeatherDto openWeatherDto;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://api.openweathermap.org/data/2.5/wether?q="+city+","+country+"&appid="+API_KEY+"&units=metric&lang=pl"))
                    .GET()
                    .build();


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }



}
