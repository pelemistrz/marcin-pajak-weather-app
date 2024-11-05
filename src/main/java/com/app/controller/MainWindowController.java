package com.app.controller;

import com.app.errors.CityNotFound;
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
import java.util.*;


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
    //error Label
    @FXML
    private Label errorDestinationCity;
    @FXML
    private Label errorLocalCity;

    private Label[] localForecastaLabelsTemp = {localForecastTemp1, localForecastTemp2, localForecastTemp3, localForecastTemp4};
    private Label[] localForecastaLabelsDesc = {localForecastDesc1, localForecastDesc2, localForecastDesc3, localForecastDesc4};

    private Label[] destinationForecastLabelTemp = {destinationForecastTemp1, destinationForecastTemp2, destinationForecastTemp3, destinationForecastTemp4};
    private Label[] destinationForecastLabelDescrip = {destinationForecastDesc1, destinationForecastDesc2, destinationForecastDesc3, destinationForecastDesc4};

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
        this.weatherService = new WeatherService();
    }

    @FXML
    void checkBtn() {
        ObservableMap<String, String> countriesMap = LoadCsv.INSTANCE.getCountriesMap();

        String destCity = this.destinationCity.getText();
        String locCity = this.localCity.getText();

        String localCountry = countriesMap.get(choiceBoxLocalCountry.getValue());
        String destinationCountry = countriesMap.get(choiceBoxDestinationCountry.getValue());

        errorLocalCity.setText("");
        errorDestinationCity.setText("");

        WeatherInfo weatherInfoLocal = null;
        try {
            weatherInfoLocal = weatherService.getTodayWeather(locCity, localCountry);
        } catch (CityNotFound e) {
            errorLocalCity.setText("City not found");
        }

        WeatherInfo weatherInfoDestination = null;
        try {
            weatherInfoDestination = weatherService.getTodayWeather(destCity, destinationCountry);
        } catch (CityNotFound e) {
            System.out.println("city not found");
            errorDestinationCity.setText("City not found");
        }

        if (weatherInfoLocal == null) {
            localTodayDescription.setText("");
            localTodayTemperature.setText("");
        } else {
            localTodayDescription.setText(weatherInfoLocal.getDescription());
            localTodayTemperature.setText(weatherInfoLocal.getTemperature() + " C");
        }

        if (weatherInfoDestination == null) {
            destinationTodayTemperature.setText("");
            destinationTodayDescription.setText("");
        } else {
            destinationTodayTemperature.setText(weatherInfoDestination.getTemperature() + " C");
            destinationTodayDescription.setText(weatherInfoDestination.getDescription());
        }

        ForecastInfo forecastInfoLocal = null;

        try {
            forecastInfoLocal = weatherService.getForecast(locCity, localCountry);
        } catch (CityNotFound e) {
            errorLocalCity.setText("City not found");
        }

        ForecastInfo forecastInfoDestination = null;
        try {
            forecastInfoDestination = weatherService.getForecast(destCity, destinationCountry);
        } catch (CityNotFound e) {
            errorDestinationCity.setText("City not found");
        }

        if (forecastInfoLocal == null) {
            setNothing(localForecastaLabelsTemp);
            setNothing(localForecastaLabelsDesc);
        } else {
            setTempOnLabel(localForecastaLabelsTemp, forecastInfoLocal);
            setDescriptionOnLabel(localForecastaLabelsDesc, forecastInfoLocal);
        }

        if (forecastInfoDestination == null) {
            setNothing(destinationForecastLabelTemp);
            setNothing(destinationForecastLabelDescrip);
        } else {
            setTempOnLabel(destinationForecastLabelTemp, forecastInfoDestination);
            setDescriptionOnLabel(destinationForecastLabelDescrip, forecastInfoDestination);
        }
    }

    private void setTempOnLabel(Label[] labels, ForecastInfo forecast) {
        for (int i = 0; i < labels.length; i++) {
            labels[i].setText(forecast.getForecastList().get(i).getTemperature() + " C");
        }
    }

    private void setDescriptionOnLabel(Label[] labels, ForecastInfo forecast) {
        for (int i = 0; i < labels.length; i++) {
            labels[i].setText(forecast.getForecastList().get(i).getDescription());
        }
    }

    private void setNothing(Label[] labels) {
        for (int i = 0; i < labels.length; i++) {
            labels[i].setText("");
        }
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
