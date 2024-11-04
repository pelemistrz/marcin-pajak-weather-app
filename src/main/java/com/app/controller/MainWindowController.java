package com.app.controller;

import com.app.model.ForecastInfo;
import com.app.model.WeatherInfo;
import com.app.services.LoadCsv;
import com.app.services.WeatherService;
import com.app.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {
    private final WeatherService weatherService;

    private ObservableList<String> countriesList = FXCollections.observableArrayList();
    @FXML
    private TextField destinationCity;
    @FXML
    private TextField localCity;
    @FXML
    private ChoiceBox<String> choiceBoxLocalCountry;
    @FXML
    private ChoiceBox<String> choiceBoxDestinationCountry;
    //Dates
    @FXML
    private Label todayLabel;
    @FXML
    private Label todayPlu2;
    @FXML
    private Label todayPlus1;
    @FXML
    private Label todayPlus3;
    @FXML
    private Label todayPlus4;
    //today weather
    @FXML
    private Label localTodayDescription;
    @FXML
    private Label localTodayTemperature;
    @FXML
    private Label destinationTodayDescription;
    @FXML
    private Label destinationTodayTemperature;
    //forecast temperature local
    @FXML
    private Label localForecastTemp1;
    @FXML
    private Label localForecastTemp2;
    @FXML
    private Label localForecastTemp3;
    @FXML
    private Label localForecastTemp4;
    //forecast description local
    @FXML
    private Label localForecastDesc1;
    @FXML
    private Label localForecastDesc2;
    @FXML
    private Label localForecastDesc3;
    @FXML
    private Label localForecastDesc4;

    //destination forecast description
    @FXML
    private Label destinationForecastDesc1;
    @FXML
    private Label destinationForecastDesc2;
    @FXML
    private Label destinationForecastDesc3;
    @FXML
    private Label destinationForecastDesc4;

    //destination forecast temperature
    @FXML
    private Label destinationForecastTemp1;
    @FXML
    private Label destinationForecastTemp2;
    @FXML
    private Label destinationForecastTemp3;
    @FXML
    private Label destinationForecastTemp4;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
        this.weatherService = new WeatherService();
    }

    @FXML
    void checkBtn() {
        ObservableMap<String,String> countriesMap = LoadCsv.INSTANCE.getCountriesMap();

        String destinationCity = this.destinationCity.getText();
        String localCity =this.localCity.getText();

        String localCountry = countriesMap.get(choiceBoxLocalCountry.getValue());
        String destinationCountry = countriesMap.get(choiceBoxDestinationCountry.getValue());

        WeatherInfo weatherInfoLocal = weatherService.getTodayWeather(localCity , localCountry);
        WeatherInfo weatherInfoDestination = weatherService.getTodayWeather(destinationCity,destinationCountry);

        localTodayDescription.setText(weatherInfoLocal.getDescription());
        localTodayTemperature.setText(weatherInfoLocal.getTemperature() + " C");
        destinationTodayTemperature.setText(weatherInfoDestination.getTemperature() + " C");
        destinationTodayDescription.setText(weatherInfoDestination.getDescription());

        ForecastInfo forecastInfoLocal = weatherService.getForecast(localCity,localCountry);
        ForecastInfo forecastInfoDestination = weatherService.getForecast(destinationCity,destinationCountry);
        //local forecast temp
        localForecastTemp1.setText(forecastInfoLocal.getForecastList().get(0).getTemperature()+" C");
        localForecastTemp2.setText(forecastInfoLocal.getForecastList().get(1).getTemperature()+" C");
        localForecastTemp3.setText(forecastInfoLocal.getForecastList().get(2).getTemperature()+" C");
        localForecastTemp4.setText(forecastInfoLocal.getForecastList().get(3).getTemperature()+" C");
        //local forecast description
        localForecastDesc1.setText(forecastInfoLocal.getForecastList().get(0).getDescription());
        localForecastDesc2.setText(forecastInfoLocal.getForecastList().get(1).getDescription());
        localForecastDesc3.setText(forecastInfoLocal.getForecastList().get(2).getDescription());
        localForecastDesc4.setText(forecastInfoLocal.getForecastList().get(3).getDescription());
        //destination forecast temp
        destinationForecastTemp1.setText(forecastInfoDestination.getForecastList().get(0).getTemperature()+" C");
        destinationForecastTemp2.setText(forecastInfoDestination.getForecastList().get(1).getTemperature()+" C");
        destinationForecastTemp3.setText(forecastInfoDestination.getForecastList().get(2).getTemperature()+" C");
        destinationForecastTemp4.setText(forecastInfoDestination.getForecastList().get(3).getTemperature()+" C");
        //destination forecast temp
        destinationForecastDesc1.setText(forecastInfoDestination.getForecastList().get(0).getDescription());
        destinationForecastDesc2.setText(forecastInfoDestination.getForecastList().get(1).getDescription());
        destinationForecastDesc3.setText(forecastInfoDestination.getForecastList().get(2).getDescription());
        destinationForecastDesc4.setText(forecastInfoDestination.getForecastList().get(3).getDescription());
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LoadCsv.INSTANCE.loadFromFile("src/main/resources/data.csv");
        ObservableList<String> countriesList = FXCollections.observableArrayList();
        countriesList.addAll(LoadCsv.INSTANCE.getCountriesMap().keySet());
        Collections.sort(countriesList);

        choiceBoxLocalCountry.setItems(countriesList);
        choiceBoxDestinationCountry.setItems(countriesList);
        choiceBoxLocalCountry.setValue("Poland");
        choiceBoxDestinationCountry.setValue("Egypt");
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        todayLabel.setText(date.format(formatter));
        todayPlus1.setText(date.plusDays(1).format(formatter));
        todayPlu2.setText(date.plusDays(2).format(formatter));
        todayPlus3.setText(date.plusDays(3).format(formatter));
        todayPlus4.setText(date.plusDays(4).format(formatter));

    }
}
