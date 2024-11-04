package com.app.webclient.weather;

import com.app.errors.CityNotFound;
import com.app.model.WeatherInfo;
import com.app.webclient.weather.dto.OpenWeatherDto;
import com.app.webclient.weather.dto.OpenWeatherForecastDto;
import com.google.gson.Gson;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherClient {
    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5";
    public static final String API_KEY = "4af1c105dcf10a54eab54afc6edd20eb";


    public OpenWeatherDto getWeatherForCity( String city,String country){
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();
       OpenWeatherDto openWeatherDto=null;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://api.openweathermap.org/data/2.5/weather?q="+city+","+country+"&appid="+API_KEY+"&units=metric&lang=pl"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
           openWeatherDto = gson.fromJson(response.body(),OpenWeatherDto.class);
            return openWeatherDto;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public OpenWeatherForecastDto getForecastForCity(String city,String country) throws CityNotFound {
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();
        OpenWeatherForecastDto openWeatherForecastDto=null;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://api.openweathermap.org/data/2.5/forecast?q="+city+","+country+"&appid="+API_KEY+"&units=metric&lang=pl"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.body().contains("city not found")){
                throw new CityNotFound("City not found");
            }
            System.out.println(response.headers());

            openWeatherForecastDto = gson.fromJson(response.body(),OpenWeatherForecastDto.class);
            System.out.println(openWeatherForecastDto.toString());
            System.out.println(openWeatherForecastDto.getList().size());
            return openWeatherForecastDto;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }



}
