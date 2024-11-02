package com.app.controller;

import com.app.services.LoadCsv;
import com.app.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    private ObservableList<String> countriesList = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> choiceBoxLocalCountry;
    @FXML
    private ChoiceBox<String> choiceBoxDestinationCountry;


    @FXML
    void checkBtn() {
        return;
    }

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory,fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       LoadCsv loadCsv = new LoadCsv();
       loadCsv.loadFromFile("src/main/resources/data.csv");

        choiceBoxLocalCountry.setItems(loadCsv.getCountriesList());
        choiceBoxDestinationCountry.setItems(loadCsv.getCountriesList());
        choiceBoxLocalCountry.setValue("Poland");
        choiceBoxDestinationCountry.setValue("Egypt");
    }
}
