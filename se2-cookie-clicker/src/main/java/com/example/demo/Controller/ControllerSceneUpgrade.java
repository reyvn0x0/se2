package com.example.demo.Controller;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo.Exceptions.PanelLoadException;
import com.example.demo.Exceptions.SoundManagerException;
import com.example.demo.Factory.IObtainble;
import com.example.demo.Main;
import com.example.demo.Managers.BrightnessManager;
import com.example.demo.Managers.IBright;
import com.example.demo.Managers.ISceneSwitcher;
import com.example.demo.Managers.ITimerManager;
import com.example.demo.Managers.IUListManager;
import com.example.demo.Managers.SceneSwitcher;
import com.example.demo.Managers.Timer;
import com.example.demo.Managers.TimerManager;
import com.example.demo.Managers.UpgradeManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ControllerSceneUpgrade{
    private static final Logger logger= Logger.getLogger(ControllerSceneUpgrade.class.getName());
    ISceneSwitcher manager=SceneSwitcher.getInstance();
    IBright bmanager= BrightnessManager.getInstance();
    IUListManager upgradeManager=UpgradeManager.getInstance();
    ITimerManager tmanager= TimerManager.getInstance();
    Image image = new Image(getClass().getResource("/Images/Checked.png").toExternalForm());
    Image starimage = new Image(getClass().getResource("/Images/Star.png").toExternalForm());
    private final ColorAdjust brightnessEffect = new ColorAdjust();
    @FXML
    VBox UpgradeSammlung = new VBox();
    @FXML
    private Pane rootPane;
    
    public void timeout() throws InterruptedException {
        if(tmanager.getTimer(1).isOn()){
            tmanager.getTimer(1).stopTimer();
            tmanager.setTimer(1, new Timer("timeouttimer"));
        }
        else{
            tmanager.getTimer(1).start();
        }
    }
    
    public void initialize() {
        brightnessEffect.setBrightness(bmanager.getBrightness());
        rootPane.setEffect(brightnessEffect);
        logger.log(Level.INFO,"ControllerSceneUpgrade wurde initaliziert!");
    }
    
    public void Upgradeadd(){
    System.out.println("DEBUG: Upgradeadd() called!");
    List<IObtainble> upgradeList = upgradeManager.getlist();
    System.out.println("DEBUG: Upgrade list size: " + upgradeList.size());
    
    try{
        for (IObtainble y : upgradeList) {
            System.out.println("DEBUG: Loading upgrade: " + y.getName());
            
            FXMLLoader fxmlLoaderUpgradepanel = new FXMLLoader(Main.class.getResource("/fxml-files/UpgradePanel.fxml"));
            Node Node = fxmlLoaderUpgradepanel.load();
            ControllerUpgradePanel controllerUpgradepanel = fxmlLoaderUpgradepanel.getController();
            
            // Set the panel data
            controllerUpgradepanel.Cost.setText(String.valueOf(y.getCost()));
            controllerUpgradepanel.Description.setText(y.getDescription());
            controllerUpgradepanel.Name.setText(y.getName());
            
            // Style based on purchase status
            if (y.getStatus() && !y.getName().equals("VICTORY")) {
                controllerUpgradepanel.Buy.setImage(image);
                controllerUpgradepanel.Upgradebox.setStyle("-fx-background-color: #35E09D;");
                System.out.println("DEBUG: Upgrade " + y.getName() + " is purchased");
                // NO BREAK HERE! Continue to next upgrade
            }
            else if(y.getName().equals("VICTORY")) {
                controllerUpgradepanel.Upgradebox.setStyle("-fx-background-color: #D641DE;");
                controllerUpgradepanel.Buy.setImage(starimage);
                System.out.println("DEBUG: VICTORY MODE!");
            }
            
            // Add to the scene
            UpgradeSammlung.getChildren().add(Node);
            System.out.println("DEBUG: Added upgrade panel for: " + y.getName());
        }
    } catch (IOException e) {
        System.out.println("DEBUG: IOException: " + e.getMessage());
        logger.log(Level.SEVERE, "Fehler beim Laden des UpgradePanels mit Fxmloader: " + e.getMessage());
    }
    upgradeManager.setlist(upgradeList);
    System.out.println("DEBUG: Upgradeadd() finished!");
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
        System.out.println("Button clicked at X: " + x); // Keep for now
        
        // ADJUSTED RANGES - shifted left
        if(375>=x && x>=352) {           // Home button (was 342-365)
            manager.switchScene("/fxml-files/HomeScene.fxml");
        }
        else if (383>=x && x>=360) {     // Upgrade button (was 375-398)
            manager.switchScene("/fxml-files/UpgradeScene.fxml");
        }
        else if (416>=x && x>=393) {     // Achievement button (was 408-431)
            manager.switchScene("/fxml-files/AchievementScene.fxml");
        }
        else if (449>=x && x>=426) {     // Settings button (was 441-464)
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
}
