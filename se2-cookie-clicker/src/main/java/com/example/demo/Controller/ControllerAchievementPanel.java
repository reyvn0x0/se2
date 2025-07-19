package com.example.demo.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ControllerAchievementPanel {
    private static final Logger logger= Logger.getLogger(ControllerAchievementPanel.class.getName());
    @FXML
    GridPane AchievementBox;
    @FXML
    Label Name;
    @FXML
    Label Description;
    public void initialize() {
        logger.log(Level.INFO,"Der Controller Achievement wurde initialisiert.");
    }
}
