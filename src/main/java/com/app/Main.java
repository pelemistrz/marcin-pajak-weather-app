package com.app;

import com.app.webclient.weather.dto.OpenWeatherDto;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
//        HttpClient client = HttpClient.newHttpClient();
//        Gson gson = new Gson();
//        OpenWeatherDto openWeatherDto=null;
//        System.out.println("wchodze do try");
//
//        try {
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(new URI("http://api.openweathermap.org/data/2.5/weather?q="+"krakow"+","+"pl"+"&appid="+"4af1c105dcf10a54eab54afc6edd20eb"+"&units=metric&lang=pl"))
//                    .GET()
//                    .build();
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
//          openWeatherDto = gson.fromJson(response.body(),OpenWeatherDto.class);
//
//           System.out.println(openWeatherDto.getMain().getTemp());
//            System.out.println(openWeatherDto.getWeather().get(0).getDescription() );
//
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//        catch(InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("End");
   }
}
