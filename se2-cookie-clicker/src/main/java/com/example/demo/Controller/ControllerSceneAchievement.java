package com.example.demo.Controller;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.demo.Exceptions.PanelLoadException;
import com.example.demo.Exceptions.SceneSwitchException;
import com.example.demo.Exceptions.SoundManagerException;
import com.example.demo.Factory.IObtainble;
import com.example.demo.Main;
import com.example.demo.Managers.AchievementManager;
import com.example.demo.Managers.BrightnessManager;
import com.example.demo.Managers.IAListManager;
import com.example.demo.Managers.IBright;
import com.example.demo.Managers.ISceneSwitcher;
import com.example.demo.Managers.ITimerManager;
import com.example.demo.Managers.SceneSwitcher;
import com.example.demo.Managers.Timer;
import com.example.demo.Managers.TimerManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
public class ControllerSceneAchievement {
    private static final Logger logger= Logger.getLogger(ControllerSceneAchievement.class.getName());
    IAListManager amanager=AchievementManager.getInstance();
    ISceneSwitcher manager= SceneSwitcher.getInstance();
    IBright bmanager = BrightnessManager.getInstance();
    ITimerManager tmanager = TimerManager.getInstance();
    @FXML
    VBox AchievementSammlung = new VBox();
    @FXML
    private Pane rootPane;
    private final ColorAdjust brightnessEffect = new ColorAdjust();
    public void initialize() {
        brightnessEffect.setBrightness(bmanager.getBrightness());
        rootPane.setEffect(brightnessEffect);
        logger.log(Level.INFO,"Der Controller Achievement wurde initialisiert.");
    }
    public void Achievementadd(){
        List<IObtainble> achievementliste = amanager.getlist();
        try{
        for (IObtainble y : achievementliste) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AchievementPanel.fxml"));
            Node Node = fxmlLoader.load();
            ControllerAchievementPanel controllerAchievementpanel = fxmlLoader.getController();
            controllerAchievementpanel.Description.setText(y.getDescription());
            controllerAchievementpanel.Name.setText(y.getDescription());
            if (y.getName().equals(controllerAchievementpanel.Name.getText()) &&!y.getName().equals("VICTORY")&&y.getStatus()){
                controllerAchievementpanel.AchievementBox.setStyle("-fx-background-color: #FFDC7C;");
                logger.log(Level.INFO,"Das Achievementpanel für " + y.getName()+ "wurde orange gefärbt!");
            }
            else if(y.getName().equals("VICTORY")){
                controllerAchievementpanel.AchievementBox.setStyle("-fx-background-color: #D641DE;");
                controllerAchievementpanel.Description.setStyle("-fx-text-fill: white;");
                controllerAchievementpanel.Name.setStyle("-fx-text-fill: white;");
                logger.log(Level.INFO,"VICTORY MODE aktivert!");
            }
            else{
                throw new PanelLoadException(y.getName());
            }
            AchievementSammlung.getChildren().add(Node);
        }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Fehler beim Laden des Achievementpanels mit Fxmloader: " + e.getMessage());
        }
        catch (PanelLoadException e) {
            logger.log(Level.WARNING, "Fehler beim Laden des Achievementpanels: " + e.getMessage());
        }
        amanager.setlist(achievementliste);
    }
    public void timeout(){
        if(tmanager.getTimer(1).isOn()) {
            tmanager.getTimer(1).stopTimer();
            tmanager.setTimer(1, new Timer("timeouttimer"));
        }
        else{
            tmanager.getTimer(1).start();
        }
    }
    public void switchToScene(MouseEvent event) throws SoundManagerException, PanelLoadException {
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
