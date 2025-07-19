package com.example.demo.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo.Exceptions.PanelLoadException;
import com.example.demo.Exceptions.SceneSwitchException;
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
public  class ControllerSceneSetting {
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

        sliderMusic.valueProperty().addListener((_, _, newValue) -> {
            mmanager.setVolume(newValue.doubleValue());
            logger.log(Level.INFO,"Musik Lautstärke ist nun " + newValue.doubleValue());
        });
    }

    private void setupBrightnessSlider(){
        sliderBrightness.valueProperty().addListener((_, _, newValue) -> {
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
        sliderSFX.valueProperty().addListener((obs, oldV, newV) -> {
            smanager.setVolume(newV.doubleValue());
            logger.log(Level.INFO,"Die Soundlautstärke ist nun " + smanager.getVolume());
        });
    }
        public void switchToScene(MouseEvent event) throws SoundManagerException, PanelLoadException  {
        try {
            if(365>=event.getSceneX()&&event.getSceneX()>=342)manager.switchScene("HomeScene.fxml");
            else if (398>=event.getSceneX()&&event.getSceneX()>=375)manager.switchScene("UpgradeScene.fxml");
            else if (431>=event.getSceneX()&&event.getSceneX()>=408)manager.switchScene("SettingScene.fxml");
            throw new SceneSwitchException("Ungültige Szene ausgewählt.");
        } catch ( SceneSwitchException e) {
            logger.log(Level.SEVERE,"Fehler beim Wechseln der Szene: " + e.getMessage());
        }
    }
}

