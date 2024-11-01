package com.app.controller;

import com.app.view.ViewFactory;
import javafx.fxml.FXML;

public class MainWindowController extends BaseController {
    @FXML
    void checkBtn() {
        return;
    }

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory,fxmlName);
    }
}
