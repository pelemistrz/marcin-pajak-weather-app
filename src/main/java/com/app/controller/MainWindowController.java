package com.app.controller;

import com.app.model.WeatherInfo;
import com.app.services.LoadCsv;
import com.app.services.WeatherService;
import com.app.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {
    private final WeatherService weatherService ;
    private ObservableList<String> countriesList = FXCollections.observableArrayList();
    @FXML
    private TextField destinationCity;
    @FXML
    private TextField localCity;
    @FXML
    private ChoiceBox<String> choiceBoxLocalCountry;
    @FXML
    private ChoiceBox<String> choiceBoxDestinationCountry;
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
    @FXML

     void checkBtn() {
        WeatherInfo weatherInfo = weatherService.getTodayWeather("krakow","pl");

    }

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory,fxmlName);
        this.weatherService = new WeatherService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       LoadCsv loadCsv = new LoadCsv();
       loadCsv.loadFromFile("src/main/resources/data.csv");
        choiceBoxLocalCountry.setItems(loadCsv.getCountriesList());
        choiceBoxDestinationCountry.setItems(loadCsv.getCountriesList());
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
