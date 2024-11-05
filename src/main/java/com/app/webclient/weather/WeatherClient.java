package com.app.webclient.weather;

import com.app.config.Config;
import com.app.errors.CityNotFound;
import com.app.model.WeatherInfo;
import com.app.webclient.weather.dto.OpenWeatherDto;
import com.app.webclient.weather.dto.OpenWeatherForecastDto;
import com.google.gson.Gson;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class WeatherClient {
    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5";


    public OpenWeatherDto getWeatherForCity( String city,String country) throws CityNotFound{
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();
       OpenWeatherDto openWeatherDto=null;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://api.openweathermap.org/data/2.5/weather?q="+URLEncoder.encode(city, StandardCharsets.UTF_8)+","+country+"&appid="+ Config.API_KEY +"&units=metric&lang=pl"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode()==404){
                throw new CityNotFound("City not found");
            } else {
                openWeatherDto = gson.fromJson(response.body(),OpenWeatherDto.class);
                return openWeatherDto;
            }
        } catch (URISyntaxException e) {
            System.out.println("uri");
        } catch(IOException e) {
            System.out.println("ioexceptio");
        }
        catch(InterruptedException e) {
            System.out.println("interrupted");
        }
        return null;
    }

    public OpenWeatherForecastDto getForecastForCity(String city,String country) throws CityNotFound {
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();
        OpenWeatherForecastDto openWeatherForecastDto=null;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://api.openweathermap.org/data/2.5/forecast?q="+ URLEncoder.encode(city, StandardCharsets.UTF_8)+","+country+"&appid="+Config.API_KEY+"&units=metric&lang=pl"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode()==404){
                throw new CityNotFound("City not found");
            } else{
                openWeatherForecastDto = gson.fromJson(response.body(),OpenWeatherForecastDto.class);
                return openWeatherForecastDto;
            }
        } catch (URISyntaxException e) {
            System.out.println("Uri syntax error");
        } catch(IOException e) {
            System.out.println("Ioexception");
        }
        catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
        return null;
    }
}
