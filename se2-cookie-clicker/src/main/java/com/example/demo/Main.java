package com.example.demo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo.Exceptions.PanelLoadException;
import com.example.demo.Exceptions.SoundManagerException;
import com.example.demo.Managers.MusicManager;
import com.example.demo.Managers.SceneSwitcher;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    MusicManager soundManager = MusicManager.getInstance();
    private static final Logger logger= Logger.getLogger(Main.class.getName());
    @Override
    public void start(Stage Primarystage) throws IOException, PanelLoadException, SoundManagerException {
        soundManager.backgroundMusic();
        SceneSwitcher.getInstance().setPrimaryStage(Primarystage);
        Primarystage.setTitle("Cookie Clicker");
        SceneSwitcher.getInstance().switchScene(SceneSwitcher.HOME_SCENE);
        Primarystage.show();
        logger.log(Level.INFO,"Anwendung wurde gestartet!");
    }
    public static void main(String[] args) {
        launch();
    }
}