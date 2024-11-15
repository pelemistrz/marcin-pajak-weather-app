package com.app.services;

import com.app.errors.CityNotFound;
import com.app.model.ForecastInfo;
import com.app.model.WeatherInfo;
import com.app.webclient.weather.WeatherClient;
import com.app.webclient.weather.dto.OpenWeatherDto;
import com.app.webclient.weather.dto.OpenWeatherForecastDto;
import com.app.webclient.weather.dto.OpenWeatherMainDto;
import com.app.webclient.weather.dto.OpenWeatherWeatherDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {
    @Mock
    WeatherClient weatherClient;

    @Test
    void getTodayWeatherShouldThrowCityNotFoundExceptionWhenThereIsNoCorrectCity() throws CityNotFound {
        //given
        WeatherService weatherService = new WeatherService(weatherClient);
        when(weatherClient.getWeatherForCity("Abrakadabra", "PL")).thenThrow(new CityNotFound("City not found"));
        //when & then
        assertThrows(CityNotFound.class,()-> weatherService.getTodayWeather("Abrakadabra","PL"));
    }

    @Test
    void getForecastShouldThrowCityNotFoundExceptionWhenThereIsNoCorrectCity() throws CityNotFound {
        //given
        WeatherService weatherService = new WeatherService(weatherClient);

        //when & then
        assertThrows(CityNotFound.class,()-> weatherService.getTodayWeather("Abrakadabra","PL"));
    }
    @Test
    void getTodayWeatherShouldReturnWeatherInfo() throws CityNotFound {
        //given
        WeatherService weatherService = new WeatherService(weatherClient);
        OpenWeatherDto openWeatherDto = new OpenWeatherDto();
        List<OpenWeatherWeatherDto> listWeateher = Arrays.asList(new OpenWeatherWeatherDto("zachmurzony"));
        openWeatherDto.setMain(new OpenWeatherMainDto(15.00));
        openWeatherDto.setWeather(listWeateher);

        when(weatherClient.getWeatherForCity("Warsaw", "PL")).thenReturn(openWeatherDto);
        //when
        WeatherInfo weatherInfo = weatherService.getTodayWeather("Warsaw","PL");

        //then
        verify(weatherClient,atLeastOnce()).getWeatherForCity("Warsaw", "PL");
        assertNotNull(weatherInfo);
        assertEquals(15.00,weatherInfo.getTemperature(),0.01);
        assertEquals("zachmurzony",weatherInfo.getDescription());

    }
    @Test
    void getForecastShouldReturnForecastInfo() throws CityNotFound{
        //given
        WeatherService weatherService = new WeatherService(weatherClient);
        OpenWeatherDto openWeatherDto = new OpenWeatherDto();
        List<OpenWeatherWeatherDto> listWeather = Arrays.asList(new OpenWeatherWeatherDto("zachmurzony"));
        openWeatherDto.setMain(new OpenWeatherMainDto(15.00));
        openWeatherDto.setWeather(listWeather);
        OpenWeatherForecastDto openWeatherForecastDto = new OpenWeatherForecastDto();

        List<OpenWeatherDto> listForecastDto = new ArrayList<>();
        for(int i=0;i<25;i++){
            listForecastDto.add(openWeatherDto);
        }
        openWeatherForecastDto.setList(listForecastDto);
        when(weatherClient.getForecastForCity("Warsaw", "PL")).thenReturn(openWeatherForecastDto);
        //when
        ForecastInfo forecastInfo = weatherService.getForecast("Warsaw","PL");

        //then
        verify(weatherClient,atLeastOnce()).getForecastForCity("Warsaw", "PL");
        assertNotNull(forecastInfo);
        assertEquals(15.00,forecastInfo.getForecastList().getFirst().getTemperature(),0.01);
        assertEquals("zachmurzony",forecastInfo.getForecastList().getFirst().getDescription());
    }
}