package com.example.demo.Controller;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo.Exceptions.PanelLoadException;
import com.example.demo.Exceptions.SoundManagerException;
import com.example.demo.Factory.IObtainble;
import com.example.demo.Managers.AchievementManager;
import com.example.demo.Managers.BrightnessManager;
import com.example.demo.Managers.CounterManager;
import com.example.demo.Managers.IAListManager;
import com.example.demo.Managers.IBright;
import com.example.demo.Managers.ICountable;
import com.example.demo.Managers.ISceneSwitcher;
import com.example.demo.Managers.ISoundManager;
import com.example.demo.Managers.ITimerManager;
import com.example.demo.Managers.IUListManager;
import com.example.demo.Managers.SceneSwitcher;
import com.example.demo.Managers.SoundManager;
import com.example.demo.Managers.Timer;
import com.example.demo.Managers.TimerManager;
import com.example.demo.Managers.UpgradeManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
public class ControllerSceneHome {
    boolean bonus;
    private static final Logger logger= Logger.getLogger(ControllerSceneHome.class.getName());
    ICountable counterManager=CounterManager.getInstance();
    ISceneSwitcher manager=SceneSwitcher.getInstance();
    IUListManager upgradeManager=UpgradeManager.getInstance();
    IBright bmanager= BrightnessManager.getInstance();
    IAListManager amanger=AchievementManager.getInstance();
    ITimerManager tmanager= TimerManager.getInstance();
    ISoundManager soundManager=SoundManager.getInstance();
    @FXML
    private Label Counter;
    @FXML
    private Pane rootPane;
    private final ColorAdjust brightnessEffect = new ColorAdjust();
    public void initialize() {
        brightnessEffect.setBrightness(bmanager.getBrightness());
        rootPane.setEffect(brightnessEffect);
        Counter.setText(Long.toString(counterManager.getCounter()));
        bonus=false;
        logger.log(Level.INFO,"ControllerHome wurde initaliziert!");
    }
                public void switchToHome(MouseEvent event) {
    try {
        manager.switchScene("/fxml-files/HomeScene.fxml");
    } catch (SoundManagerException | PanelLoadException e) {
        logger.log(Level.SEVERE,"Fehler beim Wechseln zur Home Szene: " + e.getMessage());
    }
}
public void switchToScene(MouseEvent event) {
    try {
        double x = event.getSceneX();
        System.out.println("Button clicked at X: " + x); // Keep for debugging
        
        // SHIFTED RIGHT - each range moved right by ~25px
        if(375>=x && x>=352) {           // Home button (was 327-350)
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
    public void exited(){
        if(tmanager.getTimer(0).isOn()) {
            tmanager.getTimer(0).stopTimer();
            tmanager.setTimer(0, new Timer("cookietimer"));
            logger.log(Level.INFO,"Cookie wurde verlassen und ein neuer Timer wurde gesetzt");
        }

    }
    public void timeout(){
        Timer oldTimer = tmanager.getTimer(1);
        if(oldTimer.isOn()) {
            oldTimer.stopTimer();
            tmanager.setTimer(1, new Timer("timeouttimer"));
            tmanager.getTimer(1).start();
            }
        else{
            oldTimer.start();
        }
    }
    public void increment() throws SoundManagerException {
        List<IObtainble> achievementliste = amanger.getlist();
        int increment=1;
        if(upgradeManager.domultiply()!=0){
            increment=upgradeManager.domultiply();
        }
        long num=counterManager.getCounter();
        Counter.setText(Long.toString(num+increment));
        counterManager.setCounter(Integer.parseInt(Counter.getText()));
        soundManager.playSound('c');
        logger.log(Level.INFO,"Der Counter beträgt nun: "+counterManager.getCounter());
        if(!tmanager.getTimer(0).isOn()){
            tmanager.getTimer(0).start();
            logger.info("Cookie Timer wurde gestartet.");
        }
        if(tmanager.getTimer(0).getSeconds()!=0&&tmanager.getTimer(0).getSeconds()%5==0&&!bonus){
            counterManager.setCounter(counterManager.getCounter()+30);
            bonus=true;
            logger.log(Level.INFO,"Counter wurde um 30 erhöht");
        }
        for(IObtainble x:achievementliste){
            if(x.getValue()<=counterManager.getCounter()&&!x.getName().equals("VICTORY")){
                x.setStatus(true);
                soundManager.playSound('a');
                logger.log(Level.INFO,"Der Status von " + x.getName() +" wurde auf "+x.getStatus()+"gesetzt!" );
            }
        }
        amanger.setlist(achievementliste);
        if(amanger.allactivated()&&upgradeManager.allactivated()){
            amanger.activatevictory();
            upgradeManager.activatevictory();
            logger.log(Level.INFO,"DU HAST GEWONNEN!");
        }
    }
}