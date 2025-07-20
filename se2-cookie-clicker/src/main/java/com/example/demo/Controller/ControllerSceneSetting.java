package com.example.demo.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo.Exceptions.PanelLoadException;
import com.example.demo.Exceptions.SoundManagerException;
import com.example.demo.Managers.BrightnessManager;
import com.example.demo.Managers.IBright;
import com.example.demo.Managers.IMusicManager;
import com.example.demo.Managers.ISceneSwitcher;
import com.example.demo.Managers.ISoundManager;
import com.example.demo.Managers.ITimerManager;
import com.example.demo.Managers.MusicManager;
import com.example.demo.Managers.SceneSwitcher;
import com.example.demo.Managers.SoundManager;
import com.example.demo.Managers.Timer;
import com.example.demo.Managers.TimerManager;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ControllerSceneSetting {
    private static final Logger logger= Logger.getLogger(ControllerSceneSetting.class.getName());
    ISceneSwitcher manager= SceneSwitcher.getInstance();
    IMusicManager mmanager= MusicManager.getInstance();
    IBright bmanager= BrightnessManager.getInstance();
    ITimerManager tmanager= TimerManager.getInstance();
    ISoundManager smanager= SoundManager.getInstance();
    @FXML
    protected Slider sliderSFX;
    @FXML
    protected Slider sliderMusic;
    @FXML
    protected Slider sliderBrightness;
    @FXML
    private Pane rootPane;
    private final ColorAdjust brightnessEffect = new ColorAdjust();
    
    public void timeout() throws InterruptedException {
        if(tmanager.getTimer(1).isOn()) {
            tmanager.getTimer(1).stopTimer();
            tmanager.setTimer(1, new Timer("timeouttimer"));
        }
        else{
            tmanager.getTimer(1).start();
        }
    }

    public ControllerSceneSetting() {}
    
    public void initialize(){
        setupMusicSlider();
        setupSFXSlider();
        setupBrightnessSlider();
        brightnessEffect.setBrightness(bmanager.getBrightness());
        rootPane.setEffect(brightnessEffect);
        logger.info("ControllerSettings wurde initaliziert!");
    }
    
    private void setupMusicSlider(){
        sliderMusic.setMin(0.0);
        sliderMusic.setMax(1.0);
        sliderMusic.setValue(mmanager.getVolume());

        // FIXED: Replace _ with proper parameter names
        sliderMusic.valueProperty().addListener((observable, oldValue, newValue) -> {
            mmanager.setVolume(newValue.doubleValue());
            logger.log(Level.INFO,"Musik Lautstärke ist nun " + newValue.doubleValue());
        });
    }

    private void setupBrightnessSlider(){
        // FIXED: Replace _ with proper parameter names  
        sliderBrightness.valueProperty().addListener((observable, oldValue, newValue) -> {
            double minBrightness = -0.5;
            double maxBrightness = 0.15;
           bmanager.setBrightness(minBrightness + (newValue.doubleValue() / 100.0) * (maxBrightness - minBrightness));
           brightnessEffect.setBrightness(bmanager.getBrightness());
           logger.log(Level.INFO,"Die Brightness ist nun " + bmanager.getBrightness());
        });
    }
    
    private void setupSFXSlider(){
        sliderSFX.setMin(0.0);
        sliderSFX.setMax(1.0);
        sliderSFX.setValue(smanager.getVolume());
        
        // FIXED: Replace obs, oldV, newV with proper parameter names for consistency
        sliderSFX.valueProperty().addListener((observable, oldValue, newValue) -> {
            smanager.setVolume(newValue.doubleValue());
            logger.log(Level.INFO,"Die Soundlautstärke ist nun " + smanager.getVolume());
        });
    }
public void switchToScene(MouseEvent event) {
    try {
        double x = event.getSceneX();
        System.out.println("Button clicked at X: " + x); // Keep for debugging
        
        // SHIFTED RIGHT - each range moved right by ~25px
        if(375>=x && x>=352) {          // Home button (was 327-350)
            manager.switchScene("/fxml-files/HomeScene.fxml");
        }
        else if (408>=x && x>=385) {     // Upgrade button (was 360-383) 
            manager.switchScene("/fxml-files/UpgradeScene.fxml");
        }
        else if (441>=x && x>=418) {     // Achievement button (was 393-416)
            manager.switchScene("/fxml-files/AchievementScene.fxml");
        }
        else if (474>=x && x>=451) {     // Settings button (was 426-449)
            manager.switchScene("/fxml-files/SettingScene.fxml");
        }
        else {
            System.out.println("No button matched for X coordinate: " + x);
        }
    } catch (SoundManagerException | PanelLoadException e) {
        logger.log(Level.SEVERE,"Fehler beim Wechseln der Szene: " + e.getMessage());
    }
}
    public void switchToHome(MouseEvent event) {
    try {
        manager.switchScene("/fxml-files/HomeScene.fxml");
    } catch (SoundManagerException | PanelLoadException e) {
        logger.log(Level.SEVERE,"Fehler beim Wechseln zur Home Szene: " + e.getMessage());
    }
}

public void switchToUpgrade(MouseEvent event) {
    try {
        manager.switchScene("/fxml-files/UpgradeScene.fxml");
    } catch (SoundManagerException | PanelLoadException e) {
        logger.log(Level.SEVERE,"Fehler beim Wechseln zur Upgrade Szene: " + e.getMessage());
    }
}

public void switchToAchievement(MouseEvent event) {
    try {
        manager.switchScene("/fxml-files/AchievementScene.fxml");
    } catch (SoundManagerException | PanelLoadException e) {
        logger.log(Level.SEVERE,"Fehler beim Wechseln zur Achievement Szene: " + e.getMessage());
    }
}

public void switchToSetting(MouseEvent event) {
    try {
        manager.switchScene("/fxml-files/SettingScene.fxml");
    } catch (SoundManagerException | PanelLoadException e) {
        logger.log(Level.SEVERE,"Fehler beim Wechseln zur Setting Szene: " + e.getMessage());
    }
}
}
