package com.example.demo.Managers;
import com.example.demo.Exceptions.PanelLoadException;
import com.example.demo.Exceptions.SoundManagerException;

import javafx.stage.Stage;
public interface ISceneSwitcher {
    void switchScene(String filename) throws SoundManagerException, PanelLoadException;
    void setPrimaryStage(Stage primaryStage);
}
